package org.firstinspires.ftc.teamcode.state.managers.bindings

import kotlin.math.abs
import kotlin.time.Duration

class BindingManager(
    val gamepads: Gamepads,
) {
    /**
     * Check if the action was pressed since the last call of this function.
     */
    fun binaryWasPressed(binding: BinaryBinding): Boolean {
        val gamepad = gamepads[binding.gamepad]
        return when (binding.action) {
            BinaryAction.CROSS -> gamepad.crossWasPressed()
            BinaryAction.CIRCLE -> gamepad.circleWasPressed()
            BinaryAction.TRIANGLE -> gamepad.triangleWasPressed()
            BinaryAction.SQUARE -> gamepad.squareWasPressed()
            BinaryAction.DPAD_UP -> gamepad.dpadUpWasPressed()
            BinaryAction.DPAD_DOWN -> gamepad.dpadDownWasPressed()
            BinaryAction.DPAD_LEFT -> gamepad.dpadLeftWasPressed()
            BinaryAction.DPAD_RIGHT -> gamepad.dpadRightWasPressed()
            BinaryAction.LEFT_BUMPER -> gamepad.leftBumperWasPressed()
            BinaryAction.RIGHT_BUMPER -> gamepad.rightBumperWasPressed()
            BinaryAction.LEFT_STICK_BUTTON -> gamepad.leftStickButtonWasPressed()
            BinaryAction.RIGHT_STICK_BUTTON -> gamepad.rightStickButtonWasPressed()
        }
    }

    /**
     * Check if the action was released since the last call
     */
    fun binaryWasReleased(binding: BinaryBinding): Boolean {
        val gamepad = gamepads[binding.gamepad]
        return when (binding.action) {
            BinaryAction.CROSS -> gamepad.crossWasReleased()
            BinaryAction.CIRCLE -> gamepad.circleWasReleased()
            BinaryAction.TRIANGLE -> gamepad.triangleWasReleased()
            BinaryAction.SQUARE -> gamepad.squareWasReleased()
            BinaryAction.DPAD_UP -> gamepad.dpadUpWasReleased()
            BinaryAction.DPAD_DOWN -> gamepad.dpadDownWasReleased()
            BinaryAction.DPAD_LEFT -> gamepad.dpadLeftWasReleased()
            BinaryAction.DPAD_RIGHT -> gamepad.dpadRightWasReleased()
            BinaryAction.LEFT_BUMPER -> gamepad.leftBumperWasReleased()
            BinaryAction.RIGHT_BUMPER -> gamepad.rightBumperWasReleased()
            BinaryAction.LEFT_STICK_BUTTON -> gamepad.leftStickButtonWasReleased()
            BinaryAction.RIGHT_STICK_BUTTON -> gamepad.rightStickButtonWasReleased()
        }
    }

    /**
     * Read the value of a binary binding
     */
    fun readBinary(binding: BinaryBinding): Boolean {
        val gamepad = gamepads[binding.gamepad]
        return when (binding.action) {
            BinaryAction.CROSS -> gamepad.cross
            BinaryAction.CIRCLE -> gamepad.circle
            BinaryAction.TRIANGLE -> gamepad.triangle
            BinaryAction.SQUARE -> gamepad.square
            BinaryAction.DPAD_UP -> gamepad.dpad_up
            BinaryAction.DPAD_DOWN -> gamepad.dpad_down
            BinaryAction.DPAD_LEFT -> gamepad.dpad_left
            BinaryAction.DPAD_RIGHT -> gamepad.dpad_right
            BinaryAction.LEFT_BUMPER -> gamepad.left_bumper
            BinaryAction.RIGHT_BUMPER -> gamepad.right_bumper
            BinaryAction.LEFT_STICK_BUTTON -> gamepad.left_stick_button
            BinaryAction.RIGHT_STICK_BUTTON -> gamepad.right_stick_button
        }
    }

    /**
     * Read the value of an analog binding
     */
    fun readAnalog(binding: AnalogBinding): Float {
        val gamepad = gamepads[binding.gamepad]

        val raw =
            when (binding.action) {
                AnalogAction.LEFT_STICK_X -> gamepad.left_stick_x
                AnalogAction.LEFT_STICK_Y -> -gamepad.left_stick_y
                AnalogAction.RIGHT_STICK_X -> gamepad.right_stick_x
                AnalogAction.RIGHT_STICK_Y -> -gamepad.right_stick_y
                AnalogAction.LEFT_TRIGGER -> gamepad.left_trigger
                AnalogAction.RIGHT_TRIGGER -> gamepad.right_trigger
            }
        val v = raw.coerceIn(-1f, 1f)
        return if (abs(v) < binding.deadZone) 0f else v * binding.scale
    }

    /**
     * Rumble the `targetGamepads` for a given `duration`.
     */
    fun rumble(
        duration: Duration,
        vararg targetGamepads: GamepadId,
    ) {
        for (gamepadId in targetGamepads) {
            val gamepad = gamepads[gamepadId]

            val durationMs =
                duration.inWholeMilliseconds
                    .coerceIn(0, Int.MAX_VALUE.toLong())
                    .toInt()

            gamepad.rumble(durationMs)
        }
    }
}