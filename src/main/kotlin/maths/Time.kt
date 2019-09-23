package maths

import java.math.BigDecimal

class Time(val value:Scalar) {
    constructor(number: Double) : this(Scalar(BigDecimal( number)))
}
