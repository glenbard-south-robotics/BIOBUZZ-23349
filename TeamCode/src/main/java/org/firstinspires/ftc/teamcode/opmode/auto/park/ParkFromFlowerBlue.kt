package org.firstinspires.ftc.teamcode.opmode.auto.park

import com.pedropathing.api.PoseFactory
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance

private val poseFactory = PoseFactory.degrees()

private val startCloseFlower = poseFactory.of(106.75, 10.75, 90.0)
private val parkFromCloseFlower = poseFactory.of(130.75, 25.75, 180.0)

@Autonomous(name = "ParkFromCloseFlowerBlue", group = "Park", preselectTeleOp = "BlueCompetition")
class ParkFromCloseFlowerBlue : BasePark(startCloseFlower, parkFromCloseFlower, Alliance.BLUE)

private val startFarFlower = poseFactory.of(130.75, 82.75, 180.0)
private val parkFromFarFlower = poseFactory.of(130.75, 43.75, 180.0)

@Autonomous(name = "ParkFromFarFlowerBlue", group = "Park", preselectTeleOp = "BlueCompetition")
class ParkFromFarFlowerBlue : BasePark(startFarFlower, parkFromFarFlower, Alliance.BLUE)
