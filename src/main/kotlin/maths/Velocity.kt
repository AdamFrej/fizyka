package maths

import java.math.BigDecimal

class Velocity(val vector: Vector){
    constructor(number: Double, angle:Double) : this(Vector(Scalar(BigDecimal( number)), Angle(BigDecimal(angle))))

}
