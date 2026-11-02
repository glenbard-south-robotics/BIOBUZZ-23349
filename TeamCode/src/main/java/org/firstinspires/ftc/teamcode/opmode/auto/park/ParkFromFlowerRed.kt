package org.firstinspires.ftc.teamcode.opmode.auto.park

import com.pedropathing.api.PoseFactory
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance

private val poseFactory = PoseFactory.degrees()

private val startCloseFlower = poseFactory.of(34.75, 130.75, -90.0)
private val parkFromCloseFlower = poseFactory.of(10.75, 115.75, 0.0)

@Autonomous(name = "ParkFromCloseFlowerRed", group = "Park", preselectTeleOp = "RedCompetition")
class ParkFromCloseFlowerRed : BasePark(startCloseFlower, parkFromCloseFlower, Alliance.RED)

private val startFarFlower = poseFactory.of(10.75, 58.75, 0.0)
private val parkFromFarFlower = poseFactory.of(10.75, 97.75, 0.0)

@Autonomous(name = "ParkFromFarFlowerRed", group = "Park", preselectTeleOp = "RedCompetition")
class ParkFromFarFlowerRed : BasePark(startFarFlower, parkFromFarFlower, Alliance.RED)
