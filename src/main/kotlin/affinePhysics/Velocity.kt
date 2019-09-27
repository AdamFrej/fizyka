package affinePhysics

import affinemaths.Vector

class Velocity(val vector: Vector) {
    constructor(x: String, y: String) : this(Vector(x, y))

    infix fun minus(velocity: Velocity) = Delta<Velocity>(vector minus velocity.vector)
    infix fun minus(delta: Delta<Velocity>) = Velocity(vector minus delta.vector)
    infix fun plus(delta: Delta<Velocity>) = Velocity(vector plus delta.vector)

    override fun toString(): String {
        return "Velocity($vector)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Velocity

        if (vector != other.vector) return false

        return true
    }

    override fun hashCode(): Int {
        return vector.hashCode()
    }
}