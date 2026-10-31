package org.firstinspires.ftc.teamcode.subsystems.drive

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.managers.bindings.AnalogAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.AnalogBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.GamepadId

@Configurable
object DriveBindings {
    @JvmField
    var driveX: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.LEFT_STICK_X,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var driveY: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.LEFT_STICK_Y,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var driveTurn: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.RIGHT_STICK_X,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var drivePreciseMode: BinaryBinding =
        BinaryBinding(gamepad = GamepadId.ONE, action = BinaryAction.CROSS)
}
