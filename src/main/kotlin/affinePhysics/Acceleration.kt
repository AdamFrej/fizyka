package affinePhysics

import affinemaths.Vector

class Acceleration(val vector: Vector) {
    infix fun minus(acceleration: Acceleration) = Delta<Acceleration>(vector minus acceleration.vector)
    infix fun minus(delta: Delta<Acceleration>) = Acceleration(vector minus delta.vector)
    infix fun plus(delta: Delta<Acceleration>) = Acceleration(vector plus delta.vector)
}