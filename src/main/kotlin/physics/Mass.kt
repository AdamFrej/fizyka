package physics

import maths.Scalar

class Mass(val value: Scalar) {
    constructor(number: String) : this(Scalar(number))

}
