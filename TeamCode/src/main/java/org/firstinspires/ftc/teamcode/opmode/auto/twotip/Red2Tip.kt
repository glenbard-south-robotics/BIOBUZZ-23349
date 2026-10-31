package org.firstinspires.ftc.teamcode.opmode.auto.twotip

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode

@Autonomous(
    name = "Red2Tip",
    group = "2Tip",
    preselectTeleOp = "RedTeleOp",
)
class Red2Tip : PedroCommandOpMode() {
    override fun onPedroInit() {
    }
}
