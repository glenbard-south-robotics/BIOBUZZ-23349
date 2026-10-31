package org.firstinspires.ftc.teamcode.subsystems.intake

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.MotorDirection

@Configurable
object IntakeConfig {
    @JvmField
    var intakeMotorForwardPower: Double = 1.0

    @JvmField
    var intakeMotorReversePower: Double = -0.75

    @JvmField
    var intakeMotorName: String = "intake"

    @JvmField
    var intakeMotorDirection: MotorDirection = MotorDirection.FORWARD

    @JvmField
    var gateServoName: String = "gate"

    @JvmField
    var gateServoOpenPosition: Double = 0.0

    @JvmField
    var gateServoClosedPosition: Double = 1.0
}
