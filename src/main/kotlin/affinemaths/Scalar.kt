package affinemaths

import affinemaths.NormalizationHelper.Companion.normalize
import affinemaths.NormalizationHelper.Companion.tenToPow
import java.math.MathContext
import kotlin.math.*

class Scalar : Comparable<Scalar> {
    private val value: Long
    private val exponent: Int

    constructor(value: Long, exponent: Int) {
        this.value = value
        this.exponent = exponent
    }

    private constructor(value: Long) {
        this.value = value
        this.exponent = 0
    }

    private constructor(double: Double) {
        val normalized = normalize(double, 0)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String, exponent: String) {
        val normalized = normalize(value, exponent)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String) {
        val normalized = normalize(value, "0")
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    companion object {
        val ZERO = Scalar(0, 0)
        val ONE = Scalar(10_000, 0)
        private const val precision = 5
        val mathContext = MathContext(6)
    }

    fun pow(s: String): Scalar {
        if (s.toInt() == 0) return Scalar.ONE
        var ret = Scalar(value, exponent)
        for (d: Int in 1 until s.toInt()) {
            ret = ret times this
        }
        return ret
    }

    fun atan() = Scalar(atan(toDouble()))
    fun cos() = Scalar(cos(toDouble()))
    fun sin() = Scalar(sin(toDouble()))
    fun sqrt() = Scalar(sqrt(toDouble()))
    infix fun plus(other: Scalar): Scalar {
        val relativeExponent = exponent - other.exponent
        if (relativeExponent.absoluteValue > precision)
            return if (relativeExponent > 0) Scalar(value, exponent) else Scalar(other.value, other.exponent)
        return normalize(
            add(value, other.value, relativeExponent),
            if (relativeExponent >= 0) exponent else other.exponent
        )
    }

    private fun add(left: Long, right: Long, relativeExponent: Int): Long {
        return if (relativeExponent > 0) {
            (left * tenToPow(precision) + right * tenToPow(precision) / tenToPow(relativeExponent.absoluteValue)) / tenToPow(
                precision
            )
        } else {
            (right * tenToPow(precision) + left * tenToPow(precision) / tenToPow(relativeExponent.absoluteValue)) / tenToPow(
                precision
            )
        }
    }

    operator fun unaryMinus(): Scalar = Scalar(-value, exponent)
    infix fun minus(other: Scalar) = this plus -other
    infix fun times(scalar: Scalar): Scalar {
        if (this.value == 0L || scalar.value == 0L) return ZERO
        return normalize(value * scalar.value, exponent + scalar.exponent + 1 - precision)
    }

    infix fun over(scalar: Scalar): Scalar {
        return if (this.value.absoluteValue > scalar.value.absoluteValue) {
            normalize(value * 10_000 / scalar.value, exponent - scalar.exponent)
        } else {
            normalize(value * 100_000 / scalar.value, exponent - scalar.exponent - 1)
        }
    }

    private fun isBiggerThanPrecision(value: Long): Boolean {
        return value.absoluteValue < 1_000_000
    }

    fun toDouble(): Double {
        val absoluteExponent = exponent - precision
        return if (absoluteExponent >= 0) value.toDouble() * tenToPow(absoluteExponent + 1) else value.toDouble() / tenToPow(
            -absoluteExponent - 1
        )
    }

    fun toInt(): Int {
        val absoluteExponent = exponent - precision
        val longValue: Long =
            if (absoluteExponent >= 0) value * tenToPow(absoluteExponent + 1) else value / tenToPow(
                -absoluteExponent - 1
            )
        return longValue.toInt()
    }

    fun getDrawableValue(scale: Int): Int {
        return (if (scale > 0) toDouble() / 10.0.pow(scale) else toDouble() * 10.0.pow(-scale)).toInt()
    }

    fun getScale(): Int {
        return exponent
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scalar

        if (value == 0L && other.value == 0L) return true
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
        if (!(value < 0 && other.value < 0 || value > 0 && other.value > 0)) return value.compareTo(other.value)
        return if (exponent == other.exponent) value.compareTo(other.value)
        else exponent.compareTo(other.exponent)
    }

    override fun toString(): String {
        return "$value E$exponent"
    }
}
