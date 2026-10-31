package org.firstinspires.ftc.teamcode.subsystems.indicator

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

/**
 * A simple driver class for a goBilda RGB Indicator
 */
class Indicator(
    hardwareManager: HardwareManager,
    name: String,
) {
    private val indicatorLight = hardwareManager.requireHardware<Servo>(name)
    var color = IndicatorColor.OFF

    fun update() {
        indicatorLight.position = color.pwm
    }
}
