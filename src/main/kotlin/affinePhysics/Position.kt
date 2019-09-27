package affinePhysics

import affinemaths.Vector

class Position(val point: Vector.Point) {
    constructor(x: String, y: String) : this(Vector.Point(x, y))

    infix fun minus(position: Position) = Delta<Position>(this.point minus position.point)
    infix fun plus(delta: Delta<Position>) = Position(this.point plus delta.vector)
    infix fun minus(delta: Delta<Position>) = Position(this.point minus delta.vector)
    fun distance(o: Position) = Length(point.distance(o.point))
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (point != other.point) return false

        return true
    }

    override fun hashCode(): Int {
        return point.hashCode()
    }

    override fun toString(): String {
        return "Position(point=$point)"
    }
}