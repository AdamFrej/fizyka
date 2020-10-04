package affinePhysics

import affinemaths.Scalar as MathsScalar

class Scalar<T>(internal val scalar: MathsScalar) {
    infix fun minus(other: Scalar<T>) = Delta<T>(scalar minus other.scalar)

    class Delta<T>(internal val scalar: MathsScalar) {

    }
}