package org.firstinspires.ftc.teamcode.subsystems.intake

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.state.managers.bindings.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

class Intake(
    hardwareManager: HardwareManager,
) {
    private val intakeMotor =
        hardwareManager.requireHardware<DcMotorEx>(IntakeConfig.intakeMotorName).apply {
            direction = IntakeConfig.intakeMotorDirection.toDcMotorDirection()
        }

    private val gateServo = hardwareManager.requireHardware<Servo>(IntakeConfig.gateServoName)

    var gateOpen = true
        private set

    var intakeState = IntakeState.OFF
        private set

    fun toggleGate() {
        setGateOpen(!gateOpen)
    }

    fun setGateOpen(open: Boolean) {
        this.gateOpen = open
        gateServo.position =
            if (open) IntakeConfig.gateServoOpenPosition else IntakeConfig.gateServoClosedPosition
    }

    fun setIntakeState(state: IntakeState) {
        this.intakeState = state
        intakeMotor.power =
            when (state) {
                IntakeState.FORWARD -> IntakeConfig.intakeMotorForwardPower
                IntakeState.REVERSE -> IntakeConfig.intakeMotorReversePower
                IntakeState.OFF -> 0.0
            }
    }

    fun stop() {
        setIntakeState(IntakeState.OFF)
    }

    fun openGate() = Commands.instant { setGateOpen(true) }

    fun closeGate() = Commands.instant { setGateOpen(false) }

    fun intakeForward() = Commands.instant { setIntakeState(IntakeState.FORWARD) }

    fun intakeReverse() = Commands.instant { setIntakeState(IntakeState.REVERSE) }

    fun intakeOff() = Commands.instant { setIntakeState(IntakeState.OFF) }

    fun teleopRun(bindingManager: BindingManager): Command =
        Commands.infinite {
            if (bindingManager.binaryWasPressed(IntakeBindings.toggleGate)) toggleGate()

            if (bindingManager.binaryWasPressed(IntakeBindings.toggleForward)) {
                if (intakeState ==
                    IntakeState.FORWARD
                ) {
                    stop()
                } else {
                    setIntakeState(
                        IntakeState.FORWARD,
                    )
                }
            }

            if (bindingManager.binaryWasPressed(IntakeBindings.toggleReverse)) {
                if (intakeState ==
                    IntakeState.REVERSE
                ) {
                    stop()
                } else {
                    setIntakeState(
                        IntakeState.REVERSE,
                    )
                }
            }

            if (bindingManager.binaryWasPressed(IntakeBindings.stopIntake)) stop()
        }
}
