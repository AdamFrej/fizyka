package maths

class Vector(val value: Scalar, val angle: Angle) {
    constructor(number: String, angle: String) : this(Scalar(number), Angle(angle))
    constructor(number: String, angle: Angle) : this(Scalar(number), angle)

    fun getX() = value times angle.cos()
    fun getY() = value times angle.sin()
    fun inverse() = Vector(value, (angle.inverse()))
    fun rotate(rotation: Angle) = Vector(value, angle.plus(rotation))

    infix fun plus(vector: Vector): Vector {
        val newX = getX() plus vector.getX()
        val newY = getY() plus vector.getY()
        val newAngle = (newY over newX).atan()

        return Vector(
            ((newX times newX) plus (newY times newY)).sqrt(),
            if (newX < Scalar.ZERO) newAngle.inverse() else newAngle
        )
    }

    infix fun minus(vector: Vector): Vector = plus(vector.inverse())
    infix fun times(scalar: Scalar) = Vector(value times scalar, angle)
    infix fun over(scalar: Scalar) = Vector(value over scalar, angle)
    infix fun copyAngle(scalar: Scalar) = Vector(scalar, angle)
}