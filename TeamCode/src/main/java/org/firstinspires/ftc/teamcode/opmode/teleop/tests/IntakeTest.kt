package org.firstinspires.ftc.teamcode.opmode.teleop.tests

import com.pedropathing.ivy.Scheduler.schedule
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake

@TeleOp(name = "IntakeTest", group = "Tests")
class IntakeTest : PedroCommandOpMode() {
    private lateinit var intake: Intake

    override fun onPedroInit() {
        intake = Intake(hardware)

        schedule(intake.teleopRun(bindings))
    }
}
