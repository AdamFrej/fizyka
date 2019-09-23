package maths

import ch.obermuhlner.math.big.BigDecimalMath
import java.math.BigDecimal
import java.math.MathContext

class Angle(number: BigDecimal) {
    private val context = MathContext(10)
    private val twoPi = BigDecimalMath.pi(context).times(BigDecimal(2))

    val number: BigDecimal = if(number < BigDecimal.ZERO ) number + twoPi else number %  twoPi

    infix fun plus(angle: Angle): Angle {
        return Angle(number + angle.number)
    }

}
