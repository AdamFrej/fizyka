package affinePhysics

import affinemaths.Scalar

class Time(val value: Scalar) {
    constructor(value: String) : this(Scalar(value))

    infix fun minus(other: Time): Time {
        return Time(value minus other.value)
    }
}