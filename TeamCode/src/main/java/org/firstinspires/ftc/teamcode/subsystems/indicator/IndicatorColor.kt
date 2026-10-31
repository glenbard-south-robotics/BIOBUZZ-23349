package org.firstinspires.ftc.teamcode.subsystems.indicator

sealed class IndicatorColor(
    val pwm: Double,
) {
    object OFF : IndicatorColor(0.0)

    object RED : IndicatorColor(0.277)

    object ORANGE : IndicatorColor(0.333)

    object YELLOW : IndicatorColor(0.388)

    object SAGE : IndicatorColor(0.444)

    object GREEN : IndicatorColor(0.500)

    object AZURE : IndicatorColor(0.555)

    object BLUE : IndicatorColor(0.611)

    object INDIGO : IndicatorColor(0.666)

    object VIOLET : IndicatorColor(0.722)

    object WHITE : IndicatorColor(1.0)

    class CUSTOM(
        pwm: Double,
    ) : IndicatorColor(pwm.coerceIn(0.0, 1.0))

    companion object {
        /**
         * Linearly interpolate between two colors
         */
        fun interpolate(
            lhs: IndicatorColor,
            rhs: IndicatorColor,
            factor: Double,
        ): IndicatorColor {
            val clampedFactor = factor.coerceIn(0.0, 1.0)

            if (clampedFactor == 0.0) return lhs
            if (clampedFactor == 1.0) return rhs

            val interpolatedPWM = lhs.pwm + clampedFactor * (rhs.pwm - lhs.pwm)

            return CUSTOM(interpolatedPWM)
        }
    }
}
