package org.firstinspires.ftc.teamcode.subsystems.ramp

import com.pedropathing.ivy.commands.Commands
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager
import org.firstinspires.ftc.teamcode.state.managers.bindings.BindingManager

class Ramp(
    hardwareManager: HardwareManager,
) {
    private val leftRampServo = hardwareManager.requireHardware<Servo>(RampConfig.leftRampServoName)
    private val rightRampServo =
        hardwareManager.requireHardware<Servo>(RampConfig.rightRampServoName)

    var state: RampState = RampState.UP
        private set

    fun setRampState(state: RampState) {
        this.state = state
        when (this.state) {
            RampState.UP -> {
                leftRampServo.position = RampConfig.rampServosUpPosition
                rightRampServo.position = RampConfig.rampServosUpPosition
            }

            RampState.DOWN -> {
                leftRampServo.position = RampConfig.rampServosDownPosition
                rightRampServo.position = RampConfig.rampServosDownPosition
            }
        }
    }

    fun raiseRamp() = Commands.instant { setRampState(RampState.UP) }

    fun lowerRamp() = Commands.instant { setRampState(RampState.DOWN) }

    fun teleopRun(bindingManager: BindingManager) =
        Commands.infinite {
            if (bindingManager.binaryWasPressed(RampBindings.toggleRamp)) {
                setRampState(if (this.state == RampState.UP) RampState.DOWN else RampState.UP)
            }
        }
}
