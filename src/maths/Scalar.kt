package maths

import kotlin.math.atan
import kotlin.math.sqrt

class Scalar(val number: Double, val dimension: Dimension = Dimension()) {
    infix fun plus(scalar: Scalar) = Scalar(number + scalar.number)
    infix fun times(scalar: Scalar) = Scalar(number * scalar.number)
    infix fun times(number: Double) = Scalar(this.number * number)
    infix fun over(scalar: Scalar) = Scalar(number / scalar.number)
    fun sqrt() = Scalar(sqrt(number))
    fun atan() = atan(number)

}