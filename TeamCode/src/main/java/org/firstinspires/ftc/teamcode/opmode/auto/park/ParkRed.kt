package org.firstinspires.ftc.teamcode.opmode.auto.park

import com.pedropathing.api.PoseFactory
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance

private val poseFactory = PoseFactory.degrees()

private val start = poseFactory.of(7.75, 133.75, -90.0)
private val park = poseFactory.of(10.75, 106.75, -90.0)

@Autonomous(name = "ParkRed", group = "Park", preselectTeleOp = "RedCompetition")
class ParkRed : BasePark(start, park, Alliance.RED)
