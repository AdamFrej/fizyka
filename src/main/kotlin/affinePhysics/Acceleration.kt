package affinePhysics

import affinemaths.Vector

class Acceleration(private val vector: Vector) : VectorValue {
    infix fun minus(acceleration: Acceleration) = Delta(this, acceleration)
    infix fun plus(delta: Delta<Acceleration>): Acceleration = Acceleration(delta.addTo(this.vector))
    infix fun minus(delta: Delta<Acceleration>): Acceleration = Acceleration(delta.removeFrom(this.vector))
    override fun getVector(): Vector {
        return vector
    }
}