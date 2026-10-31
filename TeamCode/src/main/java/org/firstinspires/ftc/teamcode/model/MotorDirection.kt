package org.firstinspires.ftc.teamcode.model

import com.qualcomm.robotcore.hardware.DcMotorSimple

/*
    The way a motor spins: FORWARD, or REVERSE.
 */
enum class MotorDirection(
    val coefficient: Double,
) {
    FORWARD(1.0),
    REVERSE(-1.0),
    ;

    /**
     * Apply this [MotorDirection] to a given `power`
     */
    fun apply(power: Double) = power * coefficient

    fun toDcMotorDirection(): DcMotorSimple.Direction =
        when (this) {
            FORWARD -> DcMotorSimple.Direction.FORWARD
            REVERSE -> DcMotorSimple.Direction.REVERSE
        }
}
