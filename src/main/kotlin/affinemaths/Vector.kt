package affinemaths

import ch.obermuhlner.math.big.BigDecimalMath.pi

class Vector {
    val x: Scalar
    val y: Scalar

    constructor(x: String, y: String) : this(Scalar(x), Scalar(y))
    constructor(scalar: Scalar, direction: Vector) {
        var angle = (direction.y over direction.x).atan()
        if (direction.x < Scalar.ZERO) angle = angle plus Scalar(pi(Scalar.mathContext).toString())
        this.x = scalar times angle.cos()
        this.y = scalar times angle.sin()
    }

    constructor(x: Scalar, y: Scalar) {
        this.x = x
        this.y = y
    }

    infix fun plus(vector: Vector) = Vector(x plus vector.x, y plus vector.y)
    infix fun minus(vector: Vector) = Vector(x minus vector.x, y minus vector.y)
    infix fun times(scalar: Scalar) = Vector(x times scalar, y times scalar)
    infix fun over(scalar: Scalar) = Vector(x over scalar, y over scalar)

    fun getValue(): Scalar {
        return (x.pow("2") plus y.pow("2")).sqrt()
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    companion object {
        val ZERO = Vector("0", "0")
    }
}