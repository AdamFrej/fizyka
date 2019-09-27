package affinePhysics

import affinemaths.Scalar

class Time(val value: Scalar) {
    constructor(value: String) : this(Scalar(value))
}