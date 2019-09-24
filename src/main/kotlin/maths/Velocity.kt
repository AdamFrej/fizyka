package maths

class Velocity(val vector: Vector) {
    constructor(number: String, angle: String) : this(Vector(number, angle))

}
