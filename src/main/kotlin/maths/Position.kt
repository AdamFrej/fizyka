package maths

import java.math.BigDecimal

class Position(val vector: Vector) {
    constructor(number: Double, angle: Double) : this(Vector(Scalar(BigDecimal( number)), Angle(BigDecimal(angle))))

    fun getX() = vector.getX().number.toInt()
    fun getY() = vector.getY().number.toInt()

}