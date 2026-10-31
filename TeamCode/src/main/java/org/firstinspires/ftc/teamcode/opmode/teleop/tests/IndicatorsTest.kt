package org.firstinspires.ftc.teamcode.opmode.teleop.tests

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.opmode.CommandOpMode
import org.firstinspires.ftc.teamcode.subsystems.indicator.IndicatorColor
import org.firstinspires.ftc.teamcode.subsystems.indicator.Indicators

@TeleOp(name = "IndicatorTest", group = "Tests")
class IndicatorsTest : CommandOpMode() {
    private lateinit var indicators: Indicators
    private val timer = ElapsedTime()
    private var currentIndex = 0

    val colors =
        listOf(
            IndicatorColor.OFF,
            IndicatorColor.RED,
            IndicatorColor.ORANGE,
            IndicatorColor.YELLOW,
            IndicatorColor.SAGE,
            IndicatorColor.GREEN,
            IndicatorColor.AZURE,
            IndicatorColor.BLUE,
            IndicatorColor.INDIGO,
            IndicatorColor.VIOLET,
            IndicatorColor.WHITE,
        )

    override fun onInit() {
        indicators = Indicators(hardware)
        timer.reset()
    }

    override fun onLoop() {
        if (timer.milliseconds() >= 500.0) {
            indicators.set(colors[currentIndex])
            currentIndex = (currentIndex + 1) % colors.size
            timer.reset()
        }
    }
}
