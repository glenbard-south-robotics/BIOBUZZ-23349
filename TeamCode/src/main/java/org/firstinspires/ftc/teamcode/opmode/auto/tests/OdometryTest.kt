package org.firstinspires.ftc.teamcode.opmode.auto.tests

import com.bylazar.field.PanelsField
import com.pedropathing.api.Paths
import com.pedropathing.api.PoseFactory
import com.pedropathing.ivy.groups.Groups.sequential
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode

private val poseFactory = PoseFactory.degrees()

@Autonomous(name = "OdometryTest", group = "Tests")
class OdometryTest : PedroCommandOpMode() {
    private val panelsField = PanelsField.field

    private val startPose = poseFactory.of(10.75, 10.75, 0.0)
    private val forwardPose = poseFactory.of(10.75, 34.75, 0.0)
    private val squarePose1 = poseFactory.of(34.75, 34.75, 0.0)
    private val squarePose2 = poseFactory.of(34.75, 10.75, 0.0)

    override fun onPedroInit() {
        drive.follower.setPose(startPose)

        panelsField.setOffsets(PanelsField.presets.PEDRO_PATHING)
    }

    override fun onPedroStart() {
        sequential(
            drive.follow(Paths.line(startPose, forwardPose).linear(startPose, forwardPose)),
            drive.follow(Paths.line(forwardPose, squarePose1).linear(forwardPose, squarePose1)),
            drive.follow(Paths.line(squarePose1, squarePose2).linear(squarePose1, squarePose2)),
            drive.follow(Paths.line(squarePose2, startPose).linear(squarePose2, startPose)),
            drive.hold(startPose),
        ).schedule()
    }

    override fun onPedroLoop() {
        val currentPose = drive.follower.pose()

        panelsTelemetry.addData("x", currentPose.x())
        panelsTelemetry.addData("y", currentPose.y())
        panelsTelemetry.addData("headingDegrees", Math.toDegrees(currentPose.heading()))

        panelsField.setStyle(fill = "red", outline = "white", width = 1.5)
        panelsField.moveCursor(currentPose.x(), currentPose.y())
        panelsField.circle(r = 3.0)
        panelsField.update()
    }
}
