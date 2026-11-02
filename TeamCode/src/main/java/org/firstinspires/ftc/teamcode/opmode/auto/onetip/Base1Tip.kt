package org.firstinspires.ftc.teamcode.opmode.auto.onetip

import com.pedropathing.api.Paths
import com.pedropathing.ivy.CommandBuilder
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.commands.Commands.instant
import com.pedropathing.ivy.commands.Commands.waitMs
import com.pedropathing.ivy.commands.Commands.waitUntil
import com.pedropathing.ivy.groups.Groups.parallel
import com.pedropathing.ivy.groups.Groups.race
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorConfig
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConfig
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConfig
import kotlin.math.abs

private enum class Status {
    IDLE,
    TRAVELING,
    WAITING_FOR_FLYWHEEL,
    FIRING,
    DONE,
}

abstract class Base1Tip(
    val startPose: Pose,
    val scorePose: Pose,
    val alliance: Alliance,
) : PedroCommandOpMode() {
    private var status = Status.IDLE

    fun scorePath(): Path = Paths.line(startPose, scorePose).linear(startPose, scorePose)

    private lateinit var shooter: Shooter
    private lateinit var turret: Turret
    private lateinit var intake: Intake
    private lateinit var indicators: Indicators

    override fun onPedroInit() {
        shooter = Shooter(hardware)
        turret = Turret(hardware)
        intake = Intake(hardware)
        indicators = Indicators(hardware)

        drive.follower.setPose(startPose)
    }

    fun shoot(): CommandBuilder =
        sequential(
            instant { status = Status.WAITING_FOR_FLYWHEEL },
            race(
                waitUntil { abs(ShooterConfig.flywheelVelocity - shooter.getFlywheelVelocity()) <= 50 },
                waitMs(1500.0),
            ), // make sure the flywheel is at speed
            instant { status = Status.FIRING },
            intake.intakeForward(),
            intake.openGate(),
            waitUntil { !intake.isElementDetectedAtTop() },
            waitUntil { !intake.isElementDetectedAtBottom() },
            waitMs(200.0),
            intake.closeGate(),
            intake.intakeOff(),
            instant { status = Status.IDLE },
        )

    override fun onPedroStart() {
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
                    instant { status = Status.TRAVELING },
                    drive.follow(scorePath()),
                    shoot(),
                    drive.hold(scorePose),
                    instant { status = Status.DONE },
                ),
            ),
        )
    }

    override fun onPedroLoop() {
        indicators.set(
            when (status) {
                Status.IDLE -> IndicatorConfig.allianceDefault(alliance)
                Status.TRAVELING -> IndicatorConfig.AUTO_TRAVELING
                Status.WAITING_FOR_FLYWHEEL -> IndicatorConfig.TELEOP_FLYWHEEL_SPINNING
                Status.FIRING -> IndicatorConfig.READY_TO_SHOOT
                Status.DONE -> IndicatorConfig.AUTO_DONE
            },
        )
    }

    override fun onStop() {
        RobotState.currentPose = drive.follower.pose()
    }
}
