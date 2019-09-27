package affinemaths

import ch.obermuhlner.math.big.BigDecimalMath
import java.math.BigDecimal
import java.math.MathContext

class Scalar : Comparable<Scalar> {
    private val value: BigDecimal
    private val exponent: Int

    private constructor(value: BigDecimal, exponent: Int) {
        this.value = value
        this.exponent = exponent
    }

    private constructor(value: BigDecimal) {
        val normalized = normalize(value, 0)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String, exponent: String) {
        val normalized = normalize(BigDecimal(value), exponent.toInt())
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String) {
        val normalized = normalize(BigDecimal(value), 0)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    companion object {
        val ZERO = Scalar(BigDecimal.ZERO, 0)
        val mathContext = MathContext(6)
    }

    fun pow(s: String): Scalar = normalize(value.pow(s.toInt()), exponent * s.toInt())
    fun atan() = Scalar(BigDecimalMath.atan(toBigDecimal(), mathContext))
    fun cos() = Scalar(BigDecimalMath.cos(toBigDecimal(), mathContext))
    fun sin() = Scalar(BigDecimalMath.sin(toBigDecimal(), mathContext))
    fun sqrt() = Scalar(BigDecimalMath.sqrt(toBigDecimal(), mathContext))
    infix fun plus(other: Scalar) = Scalar(toBigDecimal().plus(other.toBigDecimal()))
    infix fun minus(other: Scalar) = Scalar(toBigDecimal().minus(other.toBigDecimal()))
    infix fun times(scalar: Scalar): Scalar = normalize(this.value.times(scalar.value), exponent + scalar.exponent)
    infix fun over(scalar: Scalar): Scalar =
        normalize(this.value.divide(scalar.value, mathContext), exponent - scalar.exponent)

    private fun normalize(value: BigDecimal, exponent: Int): Scalar {
        val numberOfDigits = value.precision() - value.scale()
        val normalizedExponent = numberOfDigits - 1
        val normalizedValue =
            if (numberOfDigits > 0) value.divide(BigDecimal.TEN.pow(normalizedExponent))
            else value.times(BigDecimal.TEN.pow(-normalizedExponent))
        return Scalar(normalizedValue.stripTrailingZeros(), normalizedExponent + exponent)
    }

    private fun toBigDecimal() =
        if (exponent > 0) value.times(BigDecimal.TEN.pow(exponent)) else value.divide(BigDecimal.TEN.pow(-exponent))

    fun getDrawableValue(scale: Int): Int {
        val value =
            if (scale > 0) toBigDecimal().divide(BigDecimal.TEN.pow(scale))
            else toBigDecimal().times(BigDecimal.TEN.pow(-scale))
        return value.toInt()
    }

    fun getScale(): Int {
        return exponent
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scalar

        if (value == BigDecimal.ZERO && other.value == BigDecimal.ZERO) return true
        if (value != other.value) return false
        if (exponent != other.exponent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + exponent
        return result
    }

    override fun compareTo(other: Scalar): Int {
        if (value.signum() != other.value.signum())
            return value.signum().compareTo(other.value.signum())
        return if (exponent == other.exponent) value.compareTo(other.value)
        else exponent.compareTo(other.exponent)
    }

    override fun toString(): String {
        return "$value E$exponent"
    }
}
