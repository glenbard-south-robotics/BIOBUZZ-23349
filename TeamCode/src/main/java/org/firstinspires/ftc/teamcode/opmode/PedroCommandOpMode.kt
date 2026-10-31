package org.firstinspires.ftc.teamcode.opmode

import com.pedropathing.ivy.Scheduler
import org.firstinspires.ftc.teamcode.subsystems.drive.Drive

abstract class PedroCommandOpMode : CommandOpMode() {
    protected lateinit var drive: Drive
        private set

    final override fun onInit() {
        drive = Drive(hardware)
        onPedroInit()
    }

    final override fun onLoop() {
        drive.update()
        onPedroLoop()
    }

    protected abstract fun onPedroInit()

    protected open fun onPedroLoop() {}

    override fun start() {
        onPedroStart()
    }

    protected open fun onPedroStart() {}

    override fun onStop() {
        Scheduler.reset()
    }
}
