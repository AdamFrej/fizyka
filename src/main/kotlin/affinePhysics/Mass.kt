package affinePhysics

import affinemaths.Scalar

class Mass(val value: Scalar) {
    constructor(value: String) : this(Scalar(value))
}