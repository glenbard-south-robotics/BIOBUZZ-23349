package org.firstinspires.ftc.teamcode.opmode.auto.twotip

import com.pedropathing.api.Paths
import com.pedropathing.paths.Path
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.auto.twotip.Base2TipConfig as C

val redStartToStartingFlower: Path =
    Paths.line(C.start, C.startingFlower).linear(C.start, C.startingFlower)
val redIntoStartingFlower: Path =
    Paths.line(C.startingFlower, C.inStartingFlower).constant(C.inStartingFlower)

val redToScore: Path =
    Paths.curve(C.startingFlower, C.scoreControl, C.score).linear(C.startingFlower, C.score)
val redScoreToFlower: Path = Paths.line(C.score, C.scoringFlower).linear(C.score, C.scoringFlower)
val redIntoScoreFlower: Path =
    Paths.line(C.scoringFlower, C.inScoringFlower).constant(C.inScoringFlower)

val redScoreToPark: Path = Paths.line(C.score, C.park).constant(C.park)

@Autonomous(
    name = "Red2Tip",
    group = "2Tip",
    preselectTeleOp = "RedCompetition",
)
class Red2Tip : Base2Tip(
    redStartToStartingFlower,
    redIntoStartingFlower,
    redToScore,
    redScoreToFlower,
    redIntoScoreFlower,
    redScoreToPark,
    Alliance.RED,
)
