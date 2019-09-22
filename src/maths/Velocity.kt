package maths

class Velocity(val vector: Vector){
    constructor(number: Double, angle:Double) : this(Vector(Scalar(number), angle))

}
