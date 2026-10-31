package org.firstinspires.ftc.teamcode.pedroPathing

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.algorithm.Foresight
import com.pedropathing.algorithm.ForesightConfig
import com.pedropathing.follower.Follower
import com.pedropathing.revhub.drivetrains.Mecanum
import com.pedropathing.revhub.drivetrains.MecanumConfig
import com.pedropathing.revhub.localizers.PinpointConfig
import com.pedropathing.revhub.localizers.PinpointLocalizer
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.subsystems.drive.DriveConfig

@Configurable
object Constants {
    @JvmStatic
    var drivetrainConfig: MecanumConfig =
        MecanumConfig { c: MecanumConfig? ->
            c!!.frontLeftName.set(DriveConfig.frontLeftName)
            c.backLeftName.set(DriveConfig.backLeftName)
            c.frontRightName.set(DriveConfig.frontRightName)
            c.backRightName.set(DriveConfig.backRightName)

            c.frontLeftDirection.set(DriveConfig.frontLeftDirection.toDcMotorDirection())
            c.backLeftDirection.set(DriveConfig.backLeftDirection.toDcMotorDirection())
            c.frontRightDirection.set(DriveConfig.frontRightDirection.toDcMotorDirection())
            c.backRightDirection.set(DriveConfig.rightRearDirection.toDcMotorDirection())
        }

    @JvmStatic
    var localizerConfig: PinpointConfig =
        PinpointConfig { c: PinpointConfig? ->
            c!!.name.set("pinpoint")
        }

    @JvmStatic
    var foresightConfig: ForesightConfig =
        ForesightConfig { c: ForesightConfig? ->
        }

    fun createFollower(h: HardwareMap): Follower =
        Follower(
            PinpointLocalizer(h, localizerConfig),
            Mecanum(h, drivetrainConfig),
            Foresight(foresightConfig),
        )
}
