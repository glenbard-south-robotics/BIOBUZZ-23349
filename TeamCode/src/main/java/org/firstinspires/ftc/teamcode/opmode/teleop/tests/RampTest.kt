package org.firstinspires.ftc.teamcode.opmode.teleop.tests

import com.pedropathing.ivy.Scheduler.schedule
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.subsystems.ramp.Ramp

@TeleOp(name = "RampTest", group = "Tests")
class RampTest : PedroCommandOpMode() {
    private lateinit var ramp: Ramp

    override fun onPedroInit() {
        ramp = Ramp(hardware)

        schedule(ramp.teleopRun(bindings))
    }
}
