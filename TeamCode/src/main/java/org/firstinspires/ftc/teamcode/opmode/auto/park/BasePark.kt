package org.firstinspires.ftc.teamcode.opmode.auto.park

import com.pedropathing.api.Paths
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.RobotState
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorConfig
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators

abstract class BasePark(
    val start: Pose,
    val park: Pose,
    val alliance: Alliance,
) : PedroCommandOpMode() {
    fun parkPath(): Path = Paths.line(start, park).linear(start, park)

    private lateinit var indicators: Indicators

    override fun onPedroInit() {
        indicators = Indicators(hardware)

        drive.follower.setPose(start)
    }

    override fun onPedroStart() {
        indicators.set(IndicatorConfig.IDLE)

        sequential(
            drive.follow(parkPath()),
            drive.hold(park),
        ).schedule()
    }

    override fun onPedroLoop() {
        if (drive.follower.holding()) {
            indicators.set(IndicatorConfig.DONE_OR_PARKED)
        }
    }

    override fun onStop() {
        RobotState.currentPose = drive.follower.pose()
    }
}
