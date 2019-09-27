package affinemaths

import ch.obermuhlner.math.big.BigDecimalMath.pi

class Vector {
    private val x: Scalar
    private val y: Scalar

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

    fun getDrawableX(scale: Int): Int = x.getDrawableValue(scale)
    fun getXScale(): Int = x.getScale()
    fun getDrawableY(scale: Int): Int = y.getDrawableValue(scale)
    fun getYScale(): Int = y.getScale()
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
        return "Vector($x, $y)"
    }

    class Point {
        private val x: Scalar
        private val y: Scalar

        constructor(x: String, y: String) : this(Scalar(x), Scalar(y))
        constructor(x: Scalar, y: Scalar) {
            this.x = x
            this.y = y
        }

        infix fun plus(vector: Vector) = Point(x plus vector.x, y plus vector.y)
        infix fun minus(vector: Vector) = Point(x minus vector.x, y minus vector.y)
        infix fun minus(point: Point) = Vector(x minus point.x, y minus point.y)

        fun distance(p: Point): Scalar {
            return ((p.x minus x).pow("2") plus (p.y minus y).pow("2")).sqrt()
        }

        fun getDrawableX(scale: Int): Int = x.getDrawableValue(scale)
        fun getXScale(): Int = x.getScale()
        fun getDrawableY(scale: Int): Int = y.getDrawableValue(scale)
        fun getYScale(): Int = y.getScale()
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Point

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
            return "Point($x, $y)"
        }
    }

    companion object {
        val ZERO = Vector("0", "0")
    }
}