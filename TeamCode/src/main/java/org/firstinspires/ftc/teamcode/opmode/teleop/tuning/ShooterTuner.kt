package org.firstinspires.ftc.teamcode.opmode.teleop.tuning

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.GamepadId
import org.firstinspires.ftc.teamcode.subsystems.shooter.FlywheelState
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConfig

val adjustHoodUpBinding = BinaryBinding(
    gamepad = GamepadId.ONE, BinaryAction.DPAD_UP
)

val adjustHoodDownBinding = BinaryBinding(
    gamepad = GamepadId.ONE, BinaryAction.DPAD_DOWN
)

val flywheelVelocityUp = BinaryBinding(
    gamepad = GamepadId.ONE, BinaryAction.DPAD_RIGHT
)

val flywheelVelocityDown = BinaryBinding(
    gamepad = GamepadId.ONE, BinaryAction.DPAD_LEFT
)

val toggleFlywheel = BinaryBinding(
    gamepad = GamepadId.ONE, BinaryAction.CROSS
)

@TeleOp(name = "ShooterTuner", group = "Tuning")
class ShooterTuner : PedroCommandOpMode() {
    private lateinit var shooter: Shooter

    private var currentHoodPosition = ShooterConfig.hoodMinAnglePosition
    private var targetVelocity = ShooterConfig.flywheelVelocity

    override fun onPedroInit() {
        shooter = Shooter(hardware)
    }


    override fun onPedroLoop() {
        if (bindings.binaryWasPressed(flywheelVelocityUp)) {
            targetVelocity += 50
            shooter.setFlywheelVelocity(targetVelocity)
        }

        if (bindings.binaryWasPressed(flywheelVelocityDown)) {
            targetVelocity -= 50
            shooter.setFlywheelVelocity(targetVelocity)
        }

        if (bindings.binaryWasPressed(adjustHoodUpBinding)) {
            currentHoodPosition += 0.005
            shooter.setHoodPosition(currentHoodPosition)
        }

        if (bindings.binaryWasPressed(adjustHoodDownBinding)) {
            currentHoodPosition -= 0.005
            shooter.setHoodPosition(currentHoodPosition)
        }

        if (bindings.binaryWasPressed(toggleFlywheel)) {
            if (shooter.flywheelState == FlywheelState.OFF) {
                shooter.setFlywheelVelocity(targetVelocity)
            } else {
                shooter.setFlywheelVelocity(0.0)
            }
        }

        panelsTelemetry.addData("targetVelocity", targetVelocity)
        panelsTelemetry.addData("actualVelocity", shooter.getFlywheelVelocity())
        panelsTelemetry.addData("hoodPosition", currentHoodPosition)
    }
}