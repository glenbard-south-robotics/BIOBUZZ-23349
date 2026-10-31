package org.firstinspires.ftc.teamcode.subsystems.shooter

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.GamepadId

@Configurable
object ShooterBindings {
    @JvmField
    var toggleFlywheel =
        BinaryBinding(
            gamepad = GamepadId.TWO,
            action = BinaryAction.DPAD_UP,
        )
}
