package org.firstinspires.ftc.teamcode.opmode.auto.twotip

import com.pedropathing.api.PoseFactory
import com.pedropathing.math.Pose

object Base2TipConfig {
    private val poseFactory: PoseFactory = PoseFactory.degrees()

    val start: Pose = poseFactory.of(58.75, 10.75, 90.0)
    val startingFlower: Pose = poseFactory.of(19.75, 46.75, 180.0)
    val inStartingFlower: Pose = poseFactory.of(10.75, 46.75, 180.0)

    val score: Pose = poseFactory.of(58.75, 124.75, -90.0)
    val scoreControl: Pose = poseFactory.of(28.75, 124.75, -90.0)
    val scoringFlower: Pose = poseFactory.of(46.75, 121.75, 90.0)
    val inScoringFlower: Pose = poseFactory.of(46.75, 130.75, 90.0)

    val park: Pose = poseFactory.of(10.75, 115.75, -90.0)
}
