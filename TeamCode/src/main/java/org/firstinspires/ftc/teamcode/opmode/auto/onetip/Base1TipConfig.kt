package org.firstinspires.ftc.teamcode.opmode.auto.onetip

import com.pedropathing.api.PoseFactory
import com.pedropathing.math.Pose

object Base1TipConfig {
    private val poseFactory: PoseFactory = PoseFactory.degrees()

    val startFar: Pose = poseFactory.of(10.75, 34.75, 0.0)
    val startClose: Pose = poseFactory.of(58.75, 10.75, 90.0)
    val score: Pose = poseFactory.of(58.75, 10.75, 90.0)
}
