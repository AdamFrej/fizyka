package physics

import maths.Scalar

class Time(val value: Scalar) {
    constructor(number: String) : this(Scalar(number))
}
