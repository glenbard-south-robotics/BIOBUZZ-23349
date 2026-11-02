package org.firstinspires.ftc.teamcode.opmode.auto.twotip

import com.pedropathing.api.Paths
import com.pedropathing.paths.Path
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.util.FieldMirror
import org.firstinspires.ftc.teamcode.opmode.auto.twotip.Base2TipConfig as C

val blueStart = FieldMirror.mirrorPose(C.start)
val blueStartingFlower = FieldMirror.mirrorPose(C.startingFlower)
val blueInStartingFlower = FieldMirror.mirrorPose(C.inStartingFlower)

val blueScore = FieldMirror.mirrorPose(C.score)
val blueScoreControl = FieldMirror.mirrorPose(C.scoreControl)
val blueScoringFlower = FieldMirror.mirrorPose(C.scoringFlower)
val blueInScoringFlower = FieldMirror.mirrorPose(C.inScoringFlower)
val bluePark = FieldMirror.mirrorPose(C.park)

val blueStartToStartingFlower: Path =
    Paths.line(blueStart, blueStartingFlower).linear(blueStart, blueStartingFlower)
val blueIntoStartingFlower: Path =
    Paths.line(blueStartingFlower, blueInStartingFlower).constant(blueInStartingFlower)

val blueToScore: Path =
    Paths
        .curve(blueStartingFlower, blueScoreControl, blueScore)
        .linear(blueStartingFlower, blueScore)
val blueScoreToFlower: Path =
    Paths.line(blueScore, blueScoringFlower).linear(blueScore, blueScoringFlower)
val blueIntoScoreFlower: Path =
    Paths.line(blueScoringFlower, blueInScoringFlower).constant(blueInScoringFlower)

val blueScoreToPark: Path = Paths.line(blueScore, bluePark).constant(bluePark)

@Autonomous(
    name = "Blue2Tip",
    group = "2Tip",
    preselectTeleOp = "BlueCompetition",
)
class Blue2Tip :
    Base2Tip(
        blueStartToStartingFlower,
        blueIntoStartingFlower,
        blueToScore,
        blueScoreToFlower,
        blueIntoScoreFlower,
        blueScoreToPark,
        Alliance.BLUE,
    )
