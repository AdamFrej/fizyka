package affinePhysics

import affinemaths.Vector as MathsVector

class Vector<T>(internal val vector: MathsVector, internal val origin: MathsVector) {
    infix fun minus(other: Vector<T>) = Delta<T>(vector minus other.vector, other.vector)
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

    class Delta<T>(internal val vector: MathsVector, internal val origin: MathsVector)

    override fun toString(): String {
        return "Vector of something($vector, $origin)"
    }
}