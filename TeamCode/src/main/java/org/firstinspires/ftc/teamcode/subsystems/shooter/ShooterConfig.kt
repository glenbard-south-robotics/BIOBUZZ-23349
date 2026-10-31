package org.firstinspires.ftc.teamcode.subsystems.shooter

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo

@Configurable
object ShooterConfig {
    @JvmField
    var flywheelMotorName = "flywheel"

    @JvmField
    var hoodServoName = "hoodServo"

    @JvmField
    var hoodServoDirection = Servo.Direction.FORWARD

    @JvmField
    var hoodMinAnglePosition: Double = 0.20

    @JvmField
    var hoodMaxAnglePosition: Double = 0.75

    @JvmField
    var flywheelDirection = DcMotorSimple.Direction.FORWARD

    @JvmField
    var flywheelVelocity = 1400.0
}
