package org.firstinspires.ftc.teamcode.util

import com.pedropathing.math.Pose

object FieldMirror {
    private const val FIELD_SIZE = 141.5

    /**
     * Mirrors a PedroPathing Pose across the center of the field
     */
    fun mirrorPose(pose: Pose): Pose {
        val mirroredX = FIELD_SIZE - pose.x()
        val mirroredY = FIELD_SIZE - pose.y()

        var mirroredHeading = pose.heading() + Math.PI

        while (mirroredHeading > Math.PI) mirroredHeading -= 2.0 * Math.PI
        while (mirroredHeading <= -Math.PI) mirroredHeading += 2.0 * Math.PI

        return Pose(mirroredX, mirroredY, mirroredHeading)
    }

    /**
     * Convenience helper if working directly with X, Y, and degrees.
     */
    fun mirrorDegrees(
        x: Double,
        y: Double,
        headingDegrees: Double,
    ): Pose {
        val mirroredX = FIELD_SIZE - x
        val mirroredY = FIELD_SIZE - y

        var mirroredHeading = headingDegrees + 180.0
        while (mirroredHeading > 180.0) mirroredHeading -= 360.0
        while (mirroredHeading <= -180.0) mirroredHeading += 360.0

        return Pose(mirroredX, mirroredY, Math.toRadians(mirroredHeading))
    }
}
