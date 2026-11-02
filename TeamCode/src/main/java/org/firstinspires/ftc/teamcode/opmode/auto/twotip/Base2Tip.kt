package org.firstinspires.ftc.teamcode.opmode.auto.twotip

import com.pedropathing.ivy.CommandBuilder
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.commands.Commands.instant
import com.pedropathing.ivy.commands.Commands.waitMs
import com.pedropathing.ivy.commands.Commands.waitUntil
import com.pedropathing.ivy.groups.Groups.parallel
import com.pedropathing.ivy.groups.Groups.race
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.paths.Path
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorConfig
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.ramp.Ramp
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConfig
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConfig
import kotlin.math.abs

enum class Base2TipStatus {
    IDLE,
    FLOWERS,
    SCORING,
    PARKED,
}

abstract class Base2Tip(
    val startToFlower: Path,
    val intoFlower: Path,
    val toScore: Path,
    val scoreToFlower: Path,
    val intoScoreFlower: Path,
    val scoreToPark: Path,
    val alliance: Alliance,
) : PedroCommandOpMode() {
    var status = Base2TipStatus.IDLE

    private lateinit var turret: Turret
    private lateinit var shooter: Shooter
    private lateinit var intake: Intake
    private lateinit var ramp: Ramp
    private lateinit var indicators: Indicators

    override fun onPedroInit() {
        turret = Turret(hardware)
        shooter = Shooter(hardware)
        intake = Intake(hardware)
        ramp = Ramp(hardware)
        indicators = Indicators(hardware)

        val startingPose = startToFlower.get(0.0) // t = 0 is the start of the path

        drive.follower.setPose(startingPose)
    }

    fun shoot(): CommandBuilder =
        sequential(
            instant { status = Base2TipStatus.SCORING },
            race(
                waitUntil { abs(ShooterConfig.flywheelVelocity - shooter.getFlywheelVelocity()) <= 50 },
                waitMs(1500.0),
            ), // make sure the flywheel is at speed
            intake.intakeForward(),
            intake.openGate(),
            waitUntil { !intake.isElementDetectedAtTop() },
            waitUntil { !intake.isElementDetectedAtBottom() },
            waitMs(200.0),
            intake.closeGate(),
            intake.intakeOff(),
            instant { status = Base2TipStatus.IDLE },
        )

    fun flower(flowerPath: Path): CommandBuilder =
        sequential(
            instant { status = Base2TipStatus.FLOWERS },
            ramp.lowerRamp(),
            intake.intakeForward(),
            drive.follow(flowerPath),
            race(
                waitUntil { intake.isElementDetectedAtBottom() && intake.isElementDetectedAtTop() },
                waitMs(2000.0),
            ),
            waitMs(250.0),
            parallel(
                intake.intakeOff(),
                drive.follow(flowerPath.reverseTangent()), // back out of the flower
                sequential(
                    waitMs(1000.0), // wait so the ramp doesn't get caught on the flower
                    ramp.raiseRamp(),
                ),
            ),
            instant { status = Base2TipStatus.IDLE },
        )

    override fun onPedroStart() {
        indicators.set(IndicatorConfig.allianceDefault(alliance))

        schedule(
            parallel(
                turret.autoAimCommand(
                    currentPoseSupplier = { drive.follower.pose() },
                    alliance,
                ),
                shooter.autoTrackHoodCommand(
                    currentPoseSupplier = { drive.follower.pose() },
                    targetPoseSupplier = {
                        if (alliance == Alliance.RED) TurretConfig.redHiveClosePose else TurretConfig.blueHiveClosePose
                    },
                ),
                sequential(
                    shooter.spinUp(),
                    shoot(),
                    drive.follow(startToFlower), // go to the flower
                    flower(intoFlower),
                    drive.follow(toScore), // go to the scoring position
                    shoot(),
                    drive.follow(scoreToFlower), // go to the score flower
                    flower(intoScoreFlower),
                    drive.follow(scoreToFlower.reverseTangent()), // go back to scoring
                    shoot(),
                    drive.follow(scoreToPark), // park
                    parallel(
                        drive.hold(scoreToPark.endPose()),
                        instant { status = Base2TipStatus.PARKED },
                    ),
                ),
            ),
        )
    }

    override fun onPedroLoop() {
        indicators.set(
            when (status) {
                Base2TipStatus.IDLE -> IndicatorConfig.allianceDefault(alliance)
                Base2TipStatus.FLOWERS -> IndicatorConfig.AUTO_INTAKING
                Base2TipStatus.SCORING -> IndicatorConfig.READY_TO_SHOOT
                Base2TipStatus.PARKED -> IndicatorConfig.AUTO_DONE
            },
        )
    }

    override fun onStop() {
        RobotState.currentPose = drive.follower.pose()
    }
}
