package maths

import ch.obermuhlner.math.big.BigDecimalMath.sqrt
import ch.obermuhlner.math.big.BigDecimalMath.atan
import java.math.BigDecimal
import java.math.MathContext

class Scalar : Comparable<Scalar> {
    constructor(number: String, dimension: Dimension = Dimension()) {
        this.number = BigDecimal(number)
        this.dimension = dimension
    }

    private constructor(number: BigDecimal, dimension: Dimension = Dimension()) {
        this.number = number
        this.dimension = dimension
    }
    companion object {
        val ZERO = Scalar(BigDecimal.ZERO)
    }


    private val number: BigDecimal
    val dimension: Dimension
    private val context: MathContext = MathContext(10)

    infix fun plus(scalar: Scalar) = Scalar(number + scalar.number)
    infix fun minus(scalar: Scalar) = Scalar(number - scalar.number)
    infix fun times(scalar: Scalar) = Scalar(number * scalar.number)
    infix fun over(scalar: Scalar) = Scalar(number / scalar.number)
    fun sqrt() = Scalar(sqrt(number, context))
    fun atan() = Angle(atan(number, context).toString())
    fun toInt() = number.toInt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scalar

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }

    override fun compareTo(other: Scalar): Int = number.compareTo(other.number)
    operator fun unaryMinus(): Scalar = Scalar(-number,dimension)
    override fun toString(): String {
        return "Scalar(number=$number, dimension=$dimension)"
    }

}