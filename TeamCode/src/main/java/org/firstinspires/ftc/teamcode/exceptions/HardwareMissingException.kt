package org.firstinspires.ftc.teamcode.exceptions

class HardwareMissingException(
    id: String,
    type: String,
) : Exception("Hardware $id ($type) was not found!")
