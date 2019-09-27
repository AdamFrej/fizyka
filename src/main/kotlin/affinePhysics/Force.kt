package affinePhysics

import affinemaths.Scalar
import affinemaths.Vector

class Force(val vector: Vector) {
    constructor(x: String, y: String) : this(Vector(x, y))
    constructor(value: Scalar, direction: Vector): this(Vector(value, direction))

    infix fun plus(f: Force): Force = Force(vector plus f.vector)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Force

        if (vector != other.vector) return false

        return true
    }

    override fun hashCode(): Int {
        return vector.hashCode()
    }

    override fun toString(): String {
        return "Force($vector)"
    }
}