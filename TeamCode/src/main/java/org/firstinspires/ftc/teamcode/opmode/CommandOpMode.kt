package org.firstinspires.ftc.teamcode.opmode

import com.bylazar.telemetry.PanelsTelemetry
import com.bylazar.telemetry.TelemetryManager
import com.pedropathing.ivy.Scheduler
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.state.managers.bindings.Gamepads
import org.firstinspires.ftc.teamcode.state.managers.bindings.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

/**
 * Base OpMode that owns the Ivy [Scheduler] lifecycle and shared gamepad bindings.
 */
abstract class CommandOpMode : OpMode() {
    protected lateinit var gamepads: Gamepads
        private set

    protected lateinit var hardware: HardwareManager
        private set

    protected lateinit var bindings: BindingManager
        private set

    protected lateinit var panelsTelemetry: TelemetryManager
        private set

    protected lateinit var hubs: List<LynxModule>

    final override fun init() {
        Scheduler.reset()

        hardware = HardwareManager(hardwareMap)

        // Enable manual bulk-reads for faster loop times.
        hubs = hardware.getAll<LynxModule>()
        for (hub in hubs) {
            hub.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
        }

        panelsTelemetry = PanelsTelemetry.telemetry

        gamepads = Gamepads(gamepad1, gamepad2)
        bindings = BindingManager(gamepads)

        onInit()
    }

    final override fun loop() {
        for (hub in hubs) {
            hub.clearBulkCache()
        }

        onLoop()
        Scheduler.execute()
        panelsTelemetry.update()
    }

    final override fun stop() {
        onStop()
        Scheduler.reset()
    }

    protected abstract fun onInit()

    protected open fun onLoop() {}

    protected open fun onStop() {}
}
