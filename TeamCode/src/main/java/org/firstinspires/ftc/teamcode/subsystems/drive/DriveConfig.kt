package org.firstinspires.ftc.teamcode.subsystems.drive

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.MotorDirection
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Configurable
object DriveConfig {
    @JvmField
    var maxSpeed: Double = 1.0

    @JvmField
    var strafeMultiplier: Double = 1.0

    @JvmField
    var turnMultiplier: Double = 1.0

    @JvmField
    var preciseMultiplier: Double = 0.25

    var preciseToggleRumbleDuration: Duration = 500.milliseconds
    var preciseToggleDebounce: Duration = 500.milliseconds

    @JvmField
    var frontLeftName: String = "frontLeft"

    @JvmField
    var backLeftName: String = "backLeft"

    @JvmField
    var frontRightName: String = "frontRight"

    @JvmField
    var backRightName: String = "backRight"

    @JvmField
    var frontLeftDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var backLeftDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var frontRightDirection: MotorDirection = MotorDirection.FORWARD

    @JvmField
    var rightRearDirection: MotorDirection = MotorDirection.FORWARD
}
