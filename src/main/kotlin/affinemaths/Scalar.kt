package affinemaths

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other
import java.math.MathContext
import kotlin.math.*

class Scalar : Comparable<Scalar> {
    private val value: Long
    private val exponent: Int

    private constructor(value: Long, exponent: Int) {
        this.value = value
        this.exponent = exponent
    }

    private constructor(value: Long) {
        val normalized = normalize(value, 0)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    private constructor(double: Double) {
        val normalized = normalize(double)
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String, exponent: String) {
        val normalized = normalizeString(value.toLong(), exponent.toInt())
        this.value = normalized.value
        this.exponent = normalized.exponent
    }

    constructor(value: String) {
        val normalized = normalizeString(value.toLong(), 0)
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
        if(s.toInt()==0) return Scalar.ONE
        var ret = Scalar(value, exponent)
        for (d:Int in 1 until s.toInt()) {
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
        return normalize(value + other.value / tenToPow(relativeExponent.absoluteValue), if(relativeExponent > 0) exponent else other.exponent)
    }
    operator fun unaryMinus(): Scalar = Scalar(-value, exponent)
    infix fun minus(other: Scalar) = this plus -other
    infix fun times(scalar: Scalar): Scalar {
        if(this.value == 0L || scalar.value == 0L) return ZERO
        return normalizeBigger(this.value * scalar.value, exponent + scalar.exponent)
    }
    infix fun over(scalar: Scalar): Scalar = normalizeSmaller(this.value / scalar.value, exponent - scalar.exponent)

    private fun normalize(value: Long, exponent: Int): Scalar {
        return if (value.absoluteValue < 1_000_000) {
            normalizeSmaller(value, exponent)
        } else {
            normalizeBigger(value, exponent)
        }
    }

    private fun normalizeString(value: Long, exponent: Int): Scalar {
        return if (value.absoluteValue < 1_000_000) {
            Scalar(value * tenToPow(precision - numberOfDigits1to5(value)), numberOfDigits1to5(value) + exponent - 1)
        } else {
            Scalar(value / tenToPow(precision + numberOfDigits6to10(value)), numberOfDigits1to5(value) + exponent - 1)
        }
    }

    private fun normalize(double: Double): Scalar = normalize((double * tenToPow(precision)).toLong(), 1)

    private fun normalizeBigger(value: Long, exponent: Int): Scalar {
        return if (value.absoluteValue < 1_000_000_000) {
            if (value.absoluteValue < 100_000_000) {
                if (value.absoluteValue < 10_000_000) {
                    Scalar(value / 10, exponent + 5)
                } else {
                    Scalar(value / 100, exponent + 4)
                }
            } else {
                Scalar(value / 10_000, exponent)
            }
        } else if (value.absoluteValue >= 100_000_000) {
            Scalar(value / 100_000, exponent + 1)
        } else {
            Scalar(value / 10_000, exponent + 2)
        }
    }

    private fun normalizeSmaller(value: Long, exponent: Int): Scalar {
        return if (value.absoluteValue < 1_000) {
            if (value.absoluteValue < 100) {
                if (value.absoluteValue < 10) {
                    Scalar(value * 10_000, exponent - 4)
                } else {
                    Scalar(value * 1_000, exponent - 3)
                }
            } else {
                Scalar(value * 100, exponent - 2)
            }
        } else if (value.absoluteValue >= 10_000) {
            Scalar(value, exponent)
        } else {
            Scalar(value * 10, exponent - 1)
        }
    }

    private fun tenToPow(pow: Int): Int{
        if(pow.absoluteValue <= 1) return 10
        if(pow.absoluteValue == 2) return 100
        if(pow.absoluteValue == 3) return 1_000
        if(pow.absoluteValue == 4) return 10_000
        if(pow.absoluteValue >= 5) return 100_000
        return 0
    }

    private fun isBiggerThanPrecision(value: Long): Boolean {
        return value.absoluteValue < 1_000_000
    }

    private fun numberOfDigits1to5(value: Long): Int {
        return if (value.absoluteValue < 1_000) {
            if (value.absoluteValue < 100) {
                if (value.absoluteValue < 10) 1 else 2
            } else 3
        } else if (value.absoluteValue >= 10_000) 5 else 4
    }

    private fun numberOfDigits6to10(value: Long): Int {
        return if (value.absoluteValue < 1_000_000_000) {
            if (value.absoluteValue < 1_00_000_000) {
                if (value.absoluteValue < 10_000_000) 6 else 7
            } else 8
        } else if (value.absoluteValue >= 10_000_000_000) 10 else 9
    }

    fun toDouble(): Double {
        val absoluteExponent = 1 - exponent - precision
        return if(absoluteExponent > 0 ) value.toDouble() * tenToPow(absoluteExponent - 1) else value.toDouble() / tenToPow(-absoluteExponent)
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
