package maths

import ch.obermuhlner.math.big.BigDecimalMath
import ch.obermuhlner.math.big.BigDecimalMath.cos
import ch.obermuhlner.math.big.BigDecimalMath.sin
import java.math.BigDecimal
import java.math.MathContext

class Angle : Comparable<Angle> {
    constructor(number: String) : this(BigDecimal(number))

    private constructor(number: BigDecimal) {
        val twoPi = BigDecimalMath.pi(context).times(BigDecimal("2"))
        this.number = if (number < BigDecimal.ZERO) number + twoPi else number % twoPi
    }

    companion object {
        private val context: MathContext = MathContext(10)
        val PI = Angle(BigDecimalMath.pi(context))
    }

    private val number: BigDecimal

    infix fun plus(angle: Angle): Angle = Angle(number + angle.number)
    infix fun minus(angle: Angle): Angle = Angle(number - angle.number)
    infix fun times(string: String) = Angle(number.times(BigDecimal(string)))
    infix fun over(string: String) = Angle(number.div(BigDecimal(string)))
    fun cos() = Scalar(cos(number, context).toString())
    fun sin() = Scalar(sin(number, context).toString())
    fun inverse(): Angle = this plus PI

    override fun compareTo(other: Angle): Int = number.compareTo(other.number)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Angle

        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }

    override fun toString(): String {
        return "Angle(number=$number)"
    }

}
