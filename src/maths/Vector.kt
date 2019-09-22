package maths

import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Vector(private val value: Scalar, val angle: Double) {
    fun getX() = value times cos(angle)
    fun getY() = value times sin(angle)
    fun inverse(): Vector = Vector(value, angle - Math.PI)
    infix fun plus(vector:Vector): Vector {
        val newX = getX() plus vector.getX()
        val newY = getY() plus vector.getY()
        val newAngle = if(newX.number < 0 || newY.number < 0) (newY over newX).atan() + Math.PI else (newY over newX).atan()

        return Vector(((newX times newX) plus (newY times newY)).sqrt(), newAngle)
    }
    infix fun minus(vector: Vector):Vector = plus(vector.inverse())
    infix fun times(scalar: Scalar) = Vector(value times scalar, angle)
    infix fun over(scalar: Scalar) = Vector(value over scalar, angle)
}