package affinePhysics

import affinemaths.Scalar as MathsScalar

class Scalar<T> {
    internal val scalar: MathsScalar

    constructor(scalar: MathsScalar) {
        this.scalar = scalar
    }

    constructor(string: String) {
        this.scalar = MathsScalar(string)
    }

    infix fun minus(other: Scalar<T>) = Delta<T>(scalar minus other.scalar)

    override fun toString(): String = "Scalar($scalar)"
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scalar<*>

        if (scalar != other.scalar) return false

        return true
    }

    override fun hashCode(): Int {
        return scalar.hashCode()
    }

    class Delta<T> {
        internal val scalar: MathsScalar

        constructor(scalar: MathsScalar) {
            this.scalar = scalar
        }

        constructor(string: String) {
            this.scalar = MathsScalar(string)
        }

        override fun toString(): String = "ScalarDelta($scalar)"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Delta<*>

            if (scalar != other.scalar) return false

            return true
        }

        override fun hashCode(): Int {
            return scalar.hashCode()
        }
    }
}