package org.firstinspires.ftc.teamcode.state.managers

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.exceptions.HardwareMissingException

/**
 * A wrapper around [HardwareMap] with our custom exceptions.
 */
class HardwareManager(
    val hardwareMap: HardwareMap,
) {
    inline fun <reified T> getAll(): List<T> = hardwareMap.getAll<T>(T::class.java)

    /**
     * Get hardware by its `id`.
     * @throws HardwareMissingException if missing.
     */
    inline fun <reified T> requireHardware(id: String): T =
        hardwareMap.tryGet(T::class.java, id) ?: throw HardwareMissingException(
            id,
            T::class.simpleName ?: "Unknown",
        )
}
