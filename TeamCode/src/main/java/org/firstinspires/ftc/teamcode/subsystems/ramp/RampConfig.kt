package org.firstinspires.ftc.teamcode.subsystems.ramp

import com.bylazar.configurables.annotations.Configurable

@Configurable
object RampConfig {
    @JvmField
    var leftRampServoName: String = "leftRamp"

    @JvmField
    var rightRampServoName: String = "rightRamp"

    @JvmField
    var rampServosUpPosition: Double = 0.0

    @JvmField
    var rampServosDownPosition: Double = 1.0
}
