package org.firstinspires.ftc.teamcode.opmode.teleop.competition

import com.pedropathing.ivy.Scheduler.schedule
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.Alliance
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicator
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorColor
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeState
import org.firstinspires.ftc.teamcode.subsystems.ramp.Ramp
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretMode

@TeleOp(name = "RedTeleOp", group = "Competition")
class RedTeleOp : PedroCommandOpMode() {
    private val currentAlliance = Alliance.RED

    private lateinit var intake: Intake
    private lateinit var ramp: Ramp
    private lateinit var turret: Turret

    private lateinit var leftIndicator: Indicator
    private lateinit var rightIndicator: Indicator

    override fun onPedroInit() {
        intake = Intake(hardware)
        ramp = Ramp(hardware)
        turret = Turret(hardware)

        leftIndicator = Indicator(hardware, "leftIndicator")
        rightIndicator = Indicator(hardware, "rightIndicator")

        schedule(intake.teleopRun(bindings))
        schedule(ramp.teleopRun(bindings))

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
                turret.mode == TurretMode.AUTO_AIM_CLOSEST -> IndicatorColor.BLUE
                intake.intakeState == IntakeState.FORWARD -> IndicatorColor.GREEN
                intake.intakeState == IntakeState.REVERSE -> IndicatorColor.ORANGE
                else -> if (currentAlliance == Alliance.RED) IndicatorColor.RED else IndicatorColor.AZURE
            }

        leftIndicator.color = statusColor
        rightIndicator.color = statusColor

        leftIndicator.update()
        rightIndicator.update()
    }
}
