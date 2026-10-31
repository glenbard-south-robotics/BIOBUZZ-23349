package org.firstinspires.ftc.teamcode.opmode.teleop.competition

import com.pedropathing.ivy.Scheduler.schedule
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorColor
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeState
import org.firstinspires.ftc.teamcode.subsystems.ramp.Ramp
import org.firstinspires.ftc.teamcode.subsystems.shooter.FlywheelState
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
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
    }

    override fun onPedroLoop() {
        val statusColor =
            when {
                shooter.flywheelState == FlywheelState.SPINNING -> IndicatorColor.VIOLET
                turret.mode == TurretMode.AUTO_AIM_CLOSEST -> IndicatorColor.BLUE
                intake.intakeState == IntakeState.FORWARD -> IndicatorColor.GREEN
                intake.intakeState == IntakeState.REVERSE -> IndicatorColor.ORANGE
                else -> if (currentAlliance == Alliance.RED) IndicatorColor.RED else IndicatorColor.AZURE
            }

        indicators.set(statusColor)
    }
}
