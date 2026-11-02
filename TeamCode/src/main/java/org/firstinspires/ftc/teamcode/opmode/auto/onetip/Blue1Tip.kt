package org.firstinspires.ftc.teamcode.opmode.auto.onetip

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.util.FieldMirror

private val blueStartFarPose = FieldMirror.mirrorPose(Base1TipConfig.startFar)
private val blueScorePose = FieldMirror.mirrorPose(Base1TipConfig.score)

@Autonomous(name = "Blue1TipFar", group = "1Tip", preselectTeleOp = "BlueCompetition")
class Blue1TipFar : Base1Tip(blueStartFarPose, blueScorePose, Alliance.BLUE)

private val blueStartClosePose = FieldMirror.mirrorPose(Base1TipConfig.startClose)

@Autonomous(name = "Blue1TipClose", group = "1Tip", preselectTeleOp = "BlueCompetition")
class Blue1TipClose : Base1Tip(blueStartClosePose, blueScorePose, Alliance.BLUE)
