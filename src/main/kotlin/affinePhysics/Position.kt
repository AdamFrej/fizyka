package affinePhysics

import affinemaths.Vector

class Position: VectorValue{

    private val vector: Vector
    override fun getVector(): Vector {
        return vector
    }

    constructor(vector: Vector) {
        this.vector = vector
    }

    constructor(x:String, y:String){
        this.vector = Vector(x,y)
    }
    infix fun minus(position: Position) = Delta(this, position)
    infix fun plus(delta: Delta<Position>): Position = Position(delta.addTo(this.vector))
    infix fun minus(delta: Delta<Position>): Position = Position(delta.removeFrom(this.vector))

    override fun toString(): String {
        return "Position($vector)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (vector != other.vector) return false

        return true
    }

    override fun hashCode(): Int {
        return vector.hashCode()
    }
}