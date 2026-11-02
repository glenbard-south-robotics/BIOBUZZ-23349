package org.firstinspires.ftc.teamcode.subsystems.power

import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

typealias CurrentAmps = Double

class Power(hardwareManager: HardwareManager) {
    private val powerSwitch: AnalogInput =
        hardwareManager.requireHardware<AnalogInput>(PowerConfig.powerSwitchName)

    fun mapVoltageToCurrent(voltage: Double): CurrentAmps {
        val maxCurrent = 80.0

        return (voltage / powerSwitch.maxVoltage) * maxCurrent
    }

    fun readCurrent(): CurrentAmps {
        return mapVoltageToCurrent(powerSwitch.voltage)
    }
}