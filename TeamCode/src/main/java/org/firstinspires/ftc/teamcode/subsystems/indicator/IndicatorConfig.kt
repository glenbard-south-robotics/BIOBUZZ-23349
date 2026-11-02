package org.firstinspires.ftc.teamcode.subsystems.indicator

import org.firstinspires.ftc.teamcode.model.Alliance

object IndicatorConfig {
    fun allianceDefault(alliance: Alliance): IndicatorColor =
        if (alliance == Alliance.RED) IndicatorColor.RED else IndicatorColor.AZURE

    val READY_TO_SHOOT = IndicatorColor.GREEN
    val INTAKE_FULL = IndicatorColor.AZURE
    val ERROR_OR_REVERSE = IndicatorColor.ORANGE

    val TELEOP_FLYWHEEL_SPINNING = IndicatorColor.YELLOW
    val TELEOP_TURRET_AUTO_AIM = IndicatorColor.BLUE

    val AUTO_TRAVELING = IndicatorColor.VIOLET
    val AUTO_INTAKING = IndicatorColor.YELLOW
    val AUTO_DONE = IndicatorColor.WHITE
}