default: dev

connect: _adb-connect
# Build and deploy the code to the robot (dev).
dev: build-debug deploy-debug
# Build and deploy the code to the robot (release).
release: build-release deploy-release

sloth: _adb-connect
    gradle :TeamCode:deploySloth

fmt:
    ktlint -F

# Match CI: fail on style violations without writing fixes.
lint:
    ktlint "**/*.kt" "**/*.kts"

build-debug:
    gradle assembleDebug

build-release:
    gradle assembleRelease

deploy-debug:
    gradle installDebug

deploy-release:
    gradle installRelease

clean:
    gradle clean

toolchains:
    gradle javaToolchains


# Connect to the robot over Wi-Fi, this has to happen before uploading code.
_adb-connect:
    adb connect 192.168.43.1:5555
