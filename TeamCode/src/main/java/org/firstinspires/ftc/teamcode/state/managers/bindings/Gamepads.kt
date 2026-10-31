package org.firstinspires.ftc.teamcode.state.managers.bindings

import com.qualcomm.robotcore.hardware.Gamepad

/**
 * An identifier for one of the two [Gamepads]
 */
enum class GamepadId {
    ONE,
    TWO,
}

/**
 * A 2-tuple of FTC gamepads.
 */
data class Gamepads(
    val one: Gamepad,
    val two: Gamepad,
) {
    operator fun get(id: GamepadId): Gamepad =
        when (id) {
            GamepadId.ONE -> one
            GamepadId.TWO -> two
        }
}
