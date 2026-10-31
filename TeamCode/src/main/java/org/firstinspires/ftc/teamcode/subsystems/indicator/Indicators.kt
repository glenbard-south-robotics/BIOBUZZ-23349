package org.firstinspires.ftc.teamcode.subsystems.indicator

import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

class Indicators(
    hardwareManager: HardwareManager,
) {
    private var left = Indicator(hardwareManager, IndicatorsConfig.leftIndicatorName)
    private var right = Indicator(hardwareManager, IndicatorsConfig.rightIndicatorName)

    fun off() {
        left.color = IndicatorColor.OFF
        right.color = IndicatorColor.OFF
        left.update()
        right.update()
    }

    fun set(color: IndicatorColor) {
        left.color = color
        right.color = color
        left.update()
        right.update()
    }

    fun setLeft(color: IndicatorColor) {
        left.color = color
        left.update()
    }

    fun setRight(color: IndicatorColor) {
        right.color = color
        right.update()
    }
}
