package maths

import ch.obermuhlner.math.big.BigDecimalMath
import ch.obermuhlner.math.big.BigDecimalMath.cos
import ch.obermuhlner.math.big.BigDecimalMath.sin
import java.math.BigDecimal
import java.math.MathContext

class Vector(val value: Scalar, val angle: Angle) {
    private val context = MathContext(10)
    private val pi = BigDecimalMath.pi(context)

    constructor(number: BigDecimal, angle:BigDecimal): this(Scalar(number), Angle(angle))
    fun getX(rotation: BigDecimal = BigDecimal.ZERO) = value times cos((angle plus Angle(rotation)).number, context)
    fun getY(rotation: BigDecimal = BigDecimal.ZERO) = value times sin((angle plus Angle(rotation)).number, context)
    fun inverse() = Vector(value, (angle plus Angle(pi)))
    infix fun plus(vector: Vector): Vector {
        val newX = getX() plus vector.getX()
        val newY = getY() plus vector.getY()
        val newAngle = (newY over newX).atan() + if (newX.number < BigDecimal.ZERO)  pi else BigDecimal.ZERO

        return Vector(((newX times newX) plus (newY times newY)).sqrt(), Angle(newAngle))
    }

    infix fun minus(vector: Vector): Vector = plus(vector.inverse())
    infix fun times(scalar: Scalar) = Vector(value times scalar, angle)
    infix fun over(scalar: Scalar) = Vector(value over scalar, angle)
    infix fun copyAngle(scalar: Scalar) = Vector(scalar, angle)
}