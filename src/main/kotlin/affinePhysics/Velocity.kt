package affinePhysics

import affinemaths.Vector

class Velocity(private val vector: Vector, val position: Position) : VectorValue {
    infix fun minus(velocity: Velocity) = Delta(this, velocity)
    infix fun plus(delta: Delta<Velocity>): Velocity = Velocity(delta.addTo(this.vector), position)
    infix fun minus(delta: Delta<Velocity>): Velocity = Velocity(delta.removeFrom(this.vector), position)

    infix fun plus(velocity: Velocity): Velocity {
        if (this.position == velocity.position)
            return Velocity(vector plus velocity.vector, position)
        throw IllegalArgumentException()
    }

    override fun toString(): String {
        return "Velocity($vector)"
    }

    override fun getVector(): Vector {
        return this.vector
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