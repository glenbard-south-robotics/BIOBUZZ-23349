package org.firstinspires.ftc.teamcode.opmode.teleop.tests

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.model.Alliance
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryAction
import org.firstinspires.ftc.teamcode.state.managers.bindings.BinaryBinding
import org.firstinspires.ftc.teamcode.state.managers.bindings.GamepadId
import org.firstinspires.ftc.teamcode.subsystems.turret.Turret
import org.firstinspires.ftc.teamcode.subsystems.turret.TurretConfig

val turretLeftBinding = BinaryBinding(gamepad = GamepadId.ONE, action = BinaryAction.DPAD_LEFT)
val turretRightBinding = BinaryBinding(gamepad = GamepadId.ONE, action = BinaryAction.DPAD_RIGHT)
val turretHomeBinding = BinaryBinding(gamepad = GamepadId.ONE, action = BinaryAction.CROSS)
val turretAutoAimBinding = BinaryBinding(gamepad = GamepadId.ONE, action = BinaryAction.CIRCLE)

@TeleOp(name = "TurretTest", group = "Tests")
class TurretTest : PedroCommandOpMode() {
    private lateinit var turret: Turret
    private var testAngleDegrees = 0.0
    private val testAlliance = Alliance.BLUE

    override fun onPedroInit() {
        turret = Turret(hardware)
        testAngleDegrees = 0.0
    }

    override fun onPedroLoop() {
        if (bindings.binaryWasPressed(turretLeftBinding)) {
            testAngleDegrees =
                (testAngleDegrees - 5.0).coerceAtLeast(-TurretConfig.turretMaxAngleRight)
            turret.setTurretServoPosition(testAngleDegrees)
        } else if (bindings.binaryWasPressed(turretRightBinding)) {
            testAngleDegrees =
                (testAngleDegrees + 5.0).coerceAtMost(TurretConfig.turretMaxAngleLeft)
            turret.setTurretServoPosition(testAngleDegrees)
        }

        if (bindings.binaryWasPressed(turretHomeBinding)) {
            testAngleDegrees = 0.0
            turret.setTurretServoPosition(0.0)
        }

        if (bindings.binaryWasPressed(turretAutoAimBinding)) {
            turret.updateAutoAim(drive.follower.pose(), testAlliance)
        }

        panelsTelemetry.addData("targetAngleDegrees", testAngleDegrees)
        panelsTelemetry.addData("turretMode", turret.mode.ordinal.toDouble())
    }
}
