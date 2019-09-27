package affinePhysics

import affinemaths.Scalar

class Length(val value: Scalar): Comparable<Length> {
    constructor(value: String): this(Scalar(value))
    fun pow(s: String) = value.pow(s)
    infix fun over(length: Length) = value over length.value


    override fun compareTo(other: Length) = value.compareTo(other.value)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Length

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}
