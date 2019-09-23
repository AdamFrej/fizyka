package maths

class Position(val vector: Vector) {
    constructor(number: Double, angle: Double) : this(Vector(Scalar(number), angle))

    fun getX() = vector.getX().number.toInt()
    fun getY() = vector.getY().number.toInt()

}