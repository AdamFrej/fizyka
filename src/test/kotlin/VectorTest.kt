import ch.obermuhlner.math.big.BigDecimalMath
import maths.Vector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext

class VectorTest {
    private val context = MathContext(10)
    private val lowerContext = MathContext(9)
    private val pi = BigDecimalMath.pi(context)

    private fun piTimes(string: String) = pi.times(BigDecimal(string))
    private fun piDiv(string: String) = pi.div(BigDecimal(string))

    private fun assertEquals(expected: BigDecimal, actual: BigDecimal) =
        assertEquals(
            -1,
            (actual - expected).compareTo(BigDecimal("0.00000001")),
            "actual: %s, expected: %s".format(actual, expected)
        )

    @Test
    fun getXYTest() {
        assertEquals(BigDecimal("10"), Vector(BigDecimal("10"), BigDecimal.ZERO).getX().number)
        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), BigDecimal.ZERO).getY().number)

        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), piTimes("0.5")).getX().number)
        assertEquals(BigDecimal("10"), Vector(BigDecimal("10"), piTimes("0.5")).getY().number)

        assertEquals(BigDecimal("-10"), Vector(BigDecimal("10"), pi).getX().number)
        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), pi).getY().number)

        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), piTimes("1.5")).getX().number)
        assertEquals(BigDecimal("-10"), Vector(BigDecimal("10"), piTimes("1.5")).getY().number)

        assertEquals(BigDecimal("10"), Vector(BigDecimal("10"), piTimes("2")).getX().number)
        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), piTimes("2")).getY().number)
    }

    @Test
    fun inverseTest() {
        assertEquals(pi, Vector(BigDecimal("10"), BigDecimal.ZERO).inverse().angle.number)
        assertEquals(BigDecimal("10"), Vector(BigDecimal("10"), BigDecimal.ZERO).inverse().value.number)
        assertEquals(
            Vector(BigDecimal("9"), BigDecimal.ZERO).getX().number,
            -Vector(BigDecimal("9"), BigDecimal.ZERO).inverse().getX().number
        )
        assertEquals(
            Vector(BigDecimal("9"), piTimes("0.5")).getY().number,
            -Vector(BigDecimal("9"), piTimes("0.5")).inverse().getY().number
        )
        assertEquals(piTimes("1.5"), Vector(BigDecimal("10"), piTimes("0.5")).inverse().angle.number)
        assertEquals(BigDecimal.ZERO, Vector(BigDecimal("10"), pi).inverse().angle.number)
        assertEquals(piTimes("0.5"), Vector(BigDecimal("10"), piTimes("1.5")).inverse().angle.number)
        assertEquals(pi, Vector(BigDecimal("10"), piTimes("2")).inverse().angle.number)
    }

    @Test
    fun plusTest() {
        val vector1 = Vector(BigDecimal("3"), BigDecimal.ZERO)
        val vector2 = Vector(BigDecimal("4"), piTimes("0.5"))
        assertEquals((vector2 plus vector1).value.number, (vector1 plus vector2).value.number)
        assertEquals((vector2 plus vector1).angle.number, (vector1 plus vector2).angle.number)
        assertEquals(BigDecimal("5"), (vector1 plus vector2).value.number)
        assertEquals(BigDecimal("5"), (vector2 plus vector1).value.number)
        val vector3 = Vector(BigDecimal("9"), pi)
        val vector4 = Vector(BigDecimal("9"), piTimes("1.5"))
        assertEquals(piTimes("1.25"), (vector3 plus vector4).angle.number)
    }

    @Test
    fun minusTest() {
        val vector1 = Vector(BigDecimal("7.071067811865475"), BigDecimal.ZERO)
        val vector2 = Vector(BigDecimal("7.071067811865475"), piTimes("0.75"))
        assertEquals(piTimes("0.875"), (vector2 minus vector1).angle.number)
        assertEquals(piTimes("1.875"), (vector1 minus vector2).angle.number)
        assertEquals(BigDecimal("13.06562965"), (vector1 minus vector2).value.number)
        assertEquals(BigDecimal("13.06562965"), (vector2 minus vector1).value.number)
    }
}