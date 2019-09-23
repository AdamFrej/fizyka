package maths

import ch.obermuhlner.math.big.BigDecimalMath.sqrt
import ch.obermuhlner.math.big.BigDecimalMath.atan
import java.math.BigDecimal
import java.math.MathContext

class Scalar(val number: BigDecimal, val dimension: Dimension = Dimension()) {
    private val context = MathContext(10)
    infix fun plus(scalar: Scalar) = Scalar(number + scalar.number)
    infix fun times(scalar: Scalar) = Scalar(number * scalar.number)
    infix fun times(number: BigDecimal) = Scalar(this.number * number)
    infix fun over(scalar: Scalar) = Scalar(number / scalar.number)
    fun sqrt() = Scalar(sqrt(number, context))
    fun atan() = atan(number, context)

}