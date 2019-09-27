package physics

import maths.Vector

class Force(val vector: Vector) {
    constructor(number: String, angle: String) : this(Vector(number, angle))
}
