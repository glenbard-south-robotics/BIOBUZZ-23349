package org.firstinspires.ftc.teamcode.subsystems.indicator

import com.bylazar.configurables.annotations.Configurable

@Configurable
object IndicatorsConfig {
    @JvmField
    var leftIndicatorName: String = "leftIndicator"

    @JvmField
    var rightIndicatorName: String = "rightIndicator"
}
