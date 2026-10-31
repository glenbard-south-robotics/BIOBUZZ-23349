package org.firstinspires.ftc.teamcode.subsystems.ramp

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.GamepadId

@Configurable
object RampBindings {
    @JvmField
    var toggleRamp =
        BinaryBinding(
            gamepad = GamepadId.ONE,
            action = BinaryAction.SQUARE,
        )
}
