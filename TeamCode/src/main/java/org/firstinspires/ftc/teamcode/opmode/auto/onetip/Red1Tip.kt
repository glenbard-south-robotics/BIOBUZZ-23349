package org.firstinspires.ftc.teamcode.opmode.auto.onetip

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance

@Autonomous(name = "Red1TipFar", group = "1Tip", preselectTeleOp = "RedCompetition")
class Red1TipFar : Base1Tip(Base1TipConfig.startFar, Base1TipConfig.score, Alliance.RED)

@Autonomous(name = "Blue1TipClose", group = "1Tip", preselectTeleOp = "BlueCompetition")
class Red1TipClose : Base1Tip(Base1TipConfig.startClose, Base1TipConfig.score, Alliance.RED)