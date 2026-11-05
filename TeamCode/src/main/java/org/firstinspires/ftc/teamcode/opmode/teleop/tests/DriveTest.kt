package org.firstinspires.ftc.teamcode.opmode.teleop.tests

import com.pedropathing.ivy.Scheduler.schedule
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState

@Autonomous(name = "DriveTest", group = "Tests")
class DriveTest : PedroCommandOpMode() {
    override fun onPedroInit() {
        this.drive.follower.setPose(RobotState.currentPose)

        schedule(drive.teleopRun(bindings))
    }
}
