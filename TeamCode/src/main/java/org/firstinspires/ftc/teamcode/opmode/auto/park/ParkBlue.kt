package org.firstinspires.ftc.teamcode.opmode.auto.park

import com.pedropathing.api.PoseFactory
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance

private val poseFactory = PoseFactory.degrees()

private val start = poseFactory.of(133.75, 130.75, 90.0)
private val park = poseFactory.of(130.75, 34.75, 90.0)

@Autonomous(name = "ParkBlue", group = "Park", preselectTeleOp = "BlueCompetition")
class ParkBlue : BasePark(start, park, Alliance.BLUE)
