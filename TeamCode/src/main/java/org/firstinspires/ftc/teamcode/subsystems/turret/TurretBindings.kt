package org.firstinspires.ftc.teamcode.subsystems.turret

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.BinaryAction
import org.firstinspires.ftc.teamcode.state.BinaryBinding
import org.firstinspires.ftc.teamcode.state.GamepadId

@Configurable
object TurretBindings {
    @JvmField
    var goHome =
        BinaryBinding(
            gamepad = GamepadId.TWO,
            action = BinaryAction.SQUARE,
        )

    @JvmField
    var enableAutoAim =
        BinaryBinding(
            gamepad = GamepadId.TWO,
            action = BinaryAction.TRIANGLE,
        )
}
