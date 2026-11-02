package org.firstinspires.ftc.teamcode.opmode.teleop.competition

import com.pedropathing.ivy.Scheduler.schedule
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorConfig
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeState
import org.firstinspires.ftc.teamcode.subsystems.ramp.Ramp
import org.firstinspires.ftc.teamcode.subsystems.shooter.FlywheelState
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConfig
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretMode

abstract class BaseCompetition(
    private val currentAlliance: Alliance,
) : PedroCommandOpMode() {
    private lateinit var intake: Intake
    private lateinit var ramp: Ramp
    private lateinit var turret: Turret
    private lateinit var shooter: Shooter
    private lateinit var indicators: Indicators

    override fun onPedroInit() {
        this.drive.follower.setPose(RobotState.currentPose)

        intake = Intake(hardware)
        ramp = Ramp(hardware)
        turret = Turret(hardware)
        shooter = Shooter(hardware)
        indicators = Indicators(hardware)

        schedule(drive.teleopRun(bindings))
        schedule(intake.teleopRun(bindings))
        schedule(ramp.teleopRun(bindings))
        schedule(shooter.teleopRun(bindings))

        schedule(
            turret.teleopRun(
                bindings,
                currentPoseSupplier = { drive.follower.pose() },
                alliance = currentAlliance,
            ),
        )

        schedule(
            shooter.autoTrackHoodCommand(
                currentPoseSupplier = { drive.follower.pose() },
                targetPoseSupplier = {
                    if (currentAlliance == Alliance.RED) TurretConfig.redHiveClosePose else TurretConfig.blueHiveClosePose
                },
            ),
        )
    }

    override fun onPedroLoop() {
        val isFlywheelReady = shooter.flywheelState == FlywheelState.SPINNING
        val isTurretLocked = turret.mode == TurretMode.AUTO_AIM_CLOSEST
        val isElementLoaded = intake.isElementDetectedAtBottom() || intake.isElementDetectedAtTop()
        val isQueueFull = intake.isElementDetectedAtBottom() && intake.isElementDetectedAtTop()

        val statusColor = when {
            isFlywheelReady && isTurretLocked -> IndicatorConfig.READY_TO_SHOOT
            isQueueFull || isElementLoaded -> IndicatorConfig.INTAKE_FULL
            intake.intakeState == IntakeState.REVERSE -> IndicatorConfig.ERROR_OR_REVERSE

            shooter.flywheelState == FlywheelState.SPINNING -> IndicatorConfig.TELEOP_FLYWHEEL_SPINNING
            turret.mode == TurretMode.AUTO_AIM_CLOSEST -> IndicatorConfig.TELEOP_TURRET_AUTO_AIM

            else -> IndicatorConfig.allianceDefault(currentAlliance)
        }

        indicators.set(statusColor)
    }
}