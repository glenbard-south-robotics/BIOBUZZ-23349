package org.firstinspires.ftc.teamcode.subsystems.turret

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.state.Alliance
import org.firstinspires.ftc.teamcode.state.managers.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager
import kotlin.math.atan2
import kotlin.math.hypot

class Turret(
    hardwareManager: HardwareManager,
) {
    private val turretServo =
        hardwareManager.requireHardware<Servo>(TurretConfig.turretServoName).apply {
            this.direction = TurretConfig.turretDirection
        }

    var mode = TurretMode.HOME
        private set

    fun setTurretServoPosition(angleDegrees: Double) {
        val clampedAngle = angleDegrees.coerceIn(
            -TurretConfig.turretMaxAngleRight,
            TurretConfig.turretMaxAngleLeft,
        )

        val servoPosition =
            (clampedAngle + TurretConfig.turretMaxAngleRight) / (TurretConfig.turretMaxAngleRight + TurretConfig.turretMaxAngleLeft)
        turretServo.position = servoPosition.coerceIn(0.0, 1.0)
    }

    fun aimAtTarget(
        robotPose: Pose,
        targetPose: Pose,
    ) {
        val deltaX = targetPose.x() - robotPose.x()
        val deltaY = targetPose.y() - robotPose.y()

        val fieldAngleDegrees = Math.toDegrees(atan2(deltaY, deltaX))

        var relativeAngle = fieldAngleDegrees - Math.toDegrees(robotPose.heading())

        while (relativeAngle > 180.0) relativeAngle -= 360.0
        while (relativeAngle < -180.0) relativeAngle += 360.0

        setTurretServoPosition(relativeAngle)
    }

    fun updateAutoAim(
        robotPose: Pose,
        alliance: Alliance,
    ) {
        val closeHive =
            if (alliance == Alliance.RED) TurretConfig.redHiveClosePose else TurretConfig.blueHiveClosePose
        val farHive =
            if (alliance == Alliance.RED) TurretConfig.redHiveFarPose else TurretConfig.blueHiveFarPose

        val distClose = hypot(closeHive.x() - robotPose.x(), closeHive.y() - robotPose.y())
        val distFar = hypot(farHive.x() - robotPose.x(), farHive.y() - robotPose.y())

        val target = if (distClose <= distFar) closeHive else farHive
        aimAtTarget(robotPose, target)
    }

    fun home() = Commands.instant {
        mode = TurretMode.HOME
        setTurretServoPosition(0.0)
    }

    fun autoAimCommand(currentPoseSupplier: () -> Pose, alliance: Alliance): Command =
        Commands.infinite {
            updateAutoAim(currentPoseSupplier(), alliance)
        }

    fun teleopRun(
        bindingManager: BindingManager,
        currentPoseSupplier: () -> Pose,
        alliance: Alliance,
    ): Command = Commands.infinite {
        if (bindingManager.binaryWasPressed(TurretBindings.goHome)) {
            mode = TurretMode.HOME
            setTurretServoPosition(0.0)
        } else if (bindingManager.binaryWasPressed(TurretBindings.enableAutoAim)) {
            mode = TurretMode.AUTO_AIM_CLOSEST
        }

        if (mode == TurretMode.AUTO_AIM_CLOSEST) {
            updateAutoAim(currentPoseSupplier(), alliance)
        }
    }
}
