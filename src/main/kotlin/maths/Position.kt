package maths

class Position(val vector: Vector) {
    constructor(number: String, angle:String) : this(Vector( number, angle))
    constructor(number: String, angle:Angle) : this(Vector( number, angle))

}