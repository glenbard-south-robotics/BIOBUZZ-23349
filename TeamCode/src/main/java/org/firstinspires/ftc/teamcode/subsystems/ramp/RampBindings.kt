package org.firstinspires.ftc.teamcode.subsystems.ramp

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.BinaryAction
import org.firstinspires.ftc.teamcode.state.BinaryBinding
import org.firstinspires.ftc.teamcode.state.GamepadId

@Configurable
object RampBindings {
    @JvmField
    var toggleRamp =
        BinaryBinding(
            gamepad = GamepadId.ONE,
            action = BinaryAction.SQUARE,
        )
}
