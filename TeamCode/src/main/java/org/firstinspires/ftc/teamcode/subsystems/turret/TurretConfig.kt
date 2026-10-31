package org.firstinspires.ftc.teamcode.subsystems.turret

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.hardware.Servo

@Configurable
object TurretConfig {
    @JvmField
    var turretServoName: String = "turret"

    @JvmField
    var turretDirection = Servo.Direction.FORWARD

    @JvmField
    var turretMaxAngleLeft = 135.0

    @JvmField
    var turretMaxAngleRight = 135.0

    @JvmField
    var redHiveClosePose = Pose(58.75, 52.75)

    @JvmField
    var redHiveFarPose = Pose(58.75, 88.75)

    @JvmField
    var blueHiveClosePose = Pose(85.75, 52.75)

    @JvmField
    var blueHiveFarPose = Pose(85.75, 88.75)
}
