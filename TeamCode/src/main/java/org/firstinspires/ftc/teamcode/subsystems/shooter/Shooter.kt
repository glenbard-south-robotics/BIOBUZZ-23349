package org.firstinspires.ftc.teamcode.subsystems.shooter

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.state.managers.bindings.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager
import kotlin.math.hypot

class Shooter(
    hardwareManager: HardwareManager,
) {
    private val flywheelMotor =
        hardwareManager.requireHardware<DcMotorEx>(ShooterConfig.flywheelMotorName).apply {
            direction = ShooterConfig.flywheelDirection
            zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
            mode = DcMotor.RunMode.RUN_USING_ENCODER
        }

    private val hoodServo =
        hardwareManager.requireHardware<Servo>(ShooterConfig.hoodServoName).apply {
            direction = ShooterConfig.hoodServoDirection
        }

    var flywheelState = FlywheelState.OFF
        private set

    fun getFlywheelVelocity(): Double = flywheelMotor.velocity

    fun setFlywheelVelocity(velocity: Double) {
        if (velocity > 0.0) {
            flywheelMotor.velocity = velocity
            flywheelState = FlywheelState.SPINNING
        } else {
            flywheelMotor.power = 0.0
            flywheelState = FlywheelState.OFF
        }
    }

    fun setHoodPosition(position: Double) {
        val clampedPosition =
            position.coerceIn(
                ShooterConfig.hoodMinAnglePosition,
                ShooterConfig.hoodMaxAnglePosition,
            )
        hoodServo.position = clampedPosition
    }

    fun updateHoodByDistance(
        robotPose: Pose,
        targetPose: Pose,
    ) {
        val distance = hypot(targetPose.x() - robotPose.x(), targetPose.y() - robotPose.y())

        // TODO: Tune these values
        val minDistance = 45.0
        val maxDistance = 120.0

        val distanceFactor =
            ((distance - minDistance) / (maxDistance - minDistance)).coerceIn(0.0, 1.0)

        val targetHoodPosition =
            ShooterConfig.hoodMinAnglePosition + distanceFactor * (ShooterConfig.hoodMaxAnglePosition - ShooterConfig.hoodMinAnglePosition)

        setHoodPosition(targetHoodPosition)
    }

    fun spinUp(): Command =
        Commands.instant {
            setFlywheelVelocity(ShooterConfig.flywheelVelocity)
        }

    fun stopFlywheel(): Command =
        Commands.instant {
            setFlywheelVelocity(0.0)
        }

    fun setHoodAngle(position: Double): Command =
        Commands.instant {
            setHoodPosition(position)
        }

    fun autoTrackHoodCommand(
        currentPoseSupplier: () -> Pose,
        targetPoseSupplier: () -> Pose,
    ): Command =
        Commands.infinite {
            updateHoodByDistance(currentPoseSupplier(), targetPoseSupplier())
        }

    fun teleopRun(bindingManager: BindingManager): Command =
        Commands.infinite {
            if (bindingManager.binaryWasPressed(ShooterBindings.toggleFlywheel)) {
                if (flywheelState == FlywheelState.OFF) {
                    setFlywheelVelocity(ShooterConfig.flywheelVelocity)
                } else {
                    setFlywheelVelocity(0.0)
                }
            }
        }
}
