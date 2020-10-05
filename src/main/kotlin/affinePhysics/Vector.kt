package affinePhysics

import affinemaths.Vector as MathsVector

class Vector<T>(internal val vector: MathsVector, internal val origin: MathsVector) {
    infix fun minus(other: Vector<T>) = Delta<T>(vector minus other.vector, other.origin plus other.vector)

    infix fun plus(delta: Delta<T>): Vector<T> {
        if (delta.origin != vector)
            throw IllegalArgumentException("Delta has different origin: Vector<$vector>, Delta<$delta>")
        return Vector(vector plus delta.vector, origin)
    }

    infix fun plus(other: Vector<T>): Vector<T> {
        if (other.origin != origin)
            throw IllegalArgumentException("Delta has different origin: Vector<$vector>, Vector<$other>")
        return Vector(vector plus other.vector, origin)
    }

    override fun toString(): String {
        return "Vector($vector, $origin)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector<*>

        if (vector != other.vector) return false
        if (origin != other.origin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vector.hashCode()
        result = 31 * result + origin.hashCode()
        return result
    }

    class Delta<T>(internal val vector: MathsVector, internal val origin: MathsVector) {
        override fun toString(): String {
            return "VectorDelta($vector, $origin)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Delta<*>

            if (vector != other.vector) return false
            if (origin != other.origin) return false

            return true
        }

        override fun hashCode(): Int {
            var result = vector.hashCode()
            result = 31 * result + origin.hashCode()
            return result
        }
    }

}