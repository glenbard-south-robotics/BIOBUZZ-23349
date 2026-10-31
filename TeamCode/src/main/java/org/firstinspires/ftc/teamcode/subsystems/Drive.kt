package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.drivetrain.DrivePowers
import com.pedropathing.follower.ManualDrive
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.pedropathing.ivy.pedro.PedroCommands
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import org.firstinspires.ftc.teamcode.config.BindingsConfig
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.state.managers.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager
import kotlin.time.Clock
import kotlin.time.Instant

class Drive(
    hardwareManager: HardwareManager,
) {
    private val follower = Constants.createFollower(hardwareManager.hardwareMap)

    val pose: Pose get() = follower.pose()

    var preciseMode = false
    var preciseModeLastToggle: Instant = Instant.fromEpochSeconds(0)

    fun update() {
        follower.update()
    }

    fun setPowers(powers: DrivePowers) {
        follower.manual(powers)
    }

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
    ) {
        follower.manual(forward, strafe, turn)
    }

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
        robotCentric: Boolean = false,
    ) {
        if (robotCentric) {
            follower.manual(forward, strafe, turn)
        } else {
            follower.manual(ManualDrive.fieldCentric(forward, strafe, turn, pose.heading()))
        }
    }

    fun stop() {
        setPowers(DrivePowers.zero())
        follower.stop()
    }

    fun follow(path: Path) = PedroCommands.follow(follower, path)

    fun hold(pose: Pose?) = PedroCommands.hold(follower, pose)

    fun togglePreciseMode(bindings: BindingManager) {
        val now = Clock.System.now()
        if (now - preciseModeLastToggle < DriveConfig.preciseToggleDebounce) return

        preciseModeLastToggle = now
        preciseMode = !preciseMode
        bindings.rumble(DriveConfig.preciseToggleRumbleDuration)
    }

    fun teleopDrive(
        bindings: BindingManager,
        robotCentric: Boolean = true,
    ): Command =
        Commands
            .infinite {
                var forward =
                    bindings.readAnalog(BindingsConfig.driveY).toDouble() * DriveConfig.maxSpeed
                var strafe =
                    bindings
                        .readAnalog(BindingsConfig.driveX)
                        .toDouble() * DriveConfig.maxSpeed * DriveConfig.strafeMultiplier
                var turn =
                    bindings
                        .readAnalog(BindingsConfig.driveTurn)
                        .toDouble() * DriveConfig.maxSpeed * DriveConfig.turnMultiplier

                if (bindings.readBinary(BindingsConfig.drivePreciseMode)) {
                    togglePreciseMode(bindings)
                }

                if (preciseMode) {
                    forward *= DriveConfig.preciseMultiplier
                    strafe *= DriveConfig.preciseMultiplier
                    turn *= DriveConfig.preciseMultiplier
                }

                setPowers(forward, strafe, turn, robotCentric)
            }.setEnd { stop() }
            .requiring(this)
}
