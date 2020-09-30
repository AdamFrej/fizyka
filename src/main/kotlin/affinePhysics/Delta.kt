package affinePhysics

import affinemaths.Vector
import java.lang.IllegalArgumentException

class Delta<T: VectorValue>(val a: T, val b: T) {

    fun getVector(): Vector{
        return a.getVector() minus b.getVector()
    }

    fun addTo(vector: Vector): Vector {
        if(a.getVector() == vector) {
            return vector plus b.getVector()
        }
        if(b.getVector() == vector) {
            return vector plus a.getVector()
        }
        throw IllegalArgumentException()
    }

    fun removeFrom(vector: Vector): Vector {
        if(a.getVector() == vector) {
            return vector minus b.getVector()
        }
        if(b.getVector() == vector) {
            return vector minus a.getVector()
        }
        throw IllegalArgumentException()
    }
}