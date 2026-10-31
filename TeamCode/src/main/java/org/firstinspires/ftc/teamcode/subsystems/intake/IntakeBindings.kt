package org.firstinspires.ftc.teamcode.subsystems.intake

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.BinaryAction
import org.firstinspires.ftc.teamcode.state.BinaryBinding
import org.firstinspires.ftc.teamcode.state.GamepadId

@Configurable
object IntakeBindings {
    @JvmField
    var toggleGate = BinaryBinding(
        gamepad = GamepadId.TWO,
        action = BinaryAction.CIRCLE
    )

    @JvmField
    var toggleForward = BinaryBinding(
        gamepad = GamepadId.TWO,
        action = BinaryAction.RIGHT_BUMPER
    )

    @JvmField
    var toggleReverse = BinaryBinding(
        gamepad = GamepadId.TWO,
        action = BinaryAction.LEFT_BUMPER
    )

    @JvmField
    var stopIntake = BinaryBinding(
        gamepad = GamepadId.TWO,
        action = BinaryAction.CROSS
    )
}