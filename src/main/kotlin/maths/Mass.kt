package maths

import java.math.BigDecimal

class Mass(val value: Scalar) {
    constructor(number: Double) : this(Scalar(BigDecimal( number)))

}
