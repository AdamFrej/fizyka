package affinemaths

import ch.obermuhlner.math.big.BigDecimalMath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ScalarTest {

    @Test
    fun pow() {
        assertEquals(Scalar("25"), Scalar("5").pow("2"))
        assertEquals(Scalar("27"), Scalar("3").pow("3"))
        assertEquals(Scalar("121", "8"), Scalar("11", "4").pow("2"))
        assertEquals(Scalar("1"), Scalar("11", "4").pow("0"))
        assertEquals(Scalar("1.1", "5"), Scalar("11", "4").pow("1"))
    }

    @Test
    fun plus() {
        assertEquals(Scalar("25"), Scalar("5") plus Scalar("20"))
        assertEquals(Scalar("25"), Scalar("25") plus Scalar.ZERO)
        assertEquals(Scalar("25"), Scalar.ZERO plus Scalar("25"))
        assertEquals(Scalar("-15"), Scalar("5") plus Scalar("-20"))
        assertEquals(Scalar("123"), Scalar("1", "2") plus Scalar("23"))
        assertEquals(Scalar("12.3"), Scalar("1", "1") plus Scalar("2.3"))
        assertEquals(Scalar("654.123"), Scalar("6.54", "2") plus Scalar("1.23", "-1"))
    }

    @Test
    fun minus() {
        assertEquals(Scalar("25"), Scalar("25") minus Scalar.ZERO)
        assertEquals(Scalar("-25"), Scalar.ZERO minus Scalar("25"))
        assertEquals(Scalar("-15"), Scalar("5") minus Scalar("20"))
        assertEquals(Scalar("25"), Scalar("5") minus Scalar("-20"))
        assertEquals(Scalar("77"), Scalar("1", "2") minus Scalar("23"))
        assertEquals(Scalar("7.7"), Scalar("1", "1") minus Scalar("2.3"))
        assertEquals(Scalar("653.877"), Scalar("6.54", "2") minus Scalar("1.23", "-1"))
    }

    @Test
    fun times() {
        assertEquals(Scalar("0"), Scalar("25") times Scalar.ZERO)
        assertEquals(Scalar("0"), Scalar.ZERO times Scalar("25"))
        assertEquals(Scalar("100"), Scalar("5") times Scalar("20"))
        assertEquals(Scalar("-100"), Scalar("5") times Scalar("-20"))
        assertEquals(Scalar("99"), Scalar("-3") times Scalar("-33"))
        assertEquals(Scalar("1.5", "8"), Scalar("5", "3") times Scalar("3", "4"))
        assertEquals(Scalar("1.5"), Scalar("5", "3") times Scalar("3", "-4"))
    }

    @Test
    fun over() {
        assertEquals(Scalar("0"), Scalar.ZERO over Scalar("25"))
        assertEquals(Scalar("34.567") times Scalar("0.25"), Scalar("34.567") over Scalar("4"))
        assertEquals(Scalar("0.25"), Scalar("5") over Scalar("20"))
        assertEquals(Scalar("-0.25"), Scalar("5") over Scalar("-20"))
        assertEquals(Scalar("3"), Scalar("-99") over Scalar("-33"))
        assertEquals(Scalar("2.5", "-1"), Scalar("5", "3") over Scalar("2", "4"))
        assertEquals(Scalar("1.25", "7"), Scalar("5", "3") over Scalar("4", "-4"))
    }

    @Test
    fun atan() {
        assertAtanEquals(BigDecimal("7.7"))
        assertAtanEquals(BigDecimal("-7.7"))
        assertAtanEquals(BigDecimalMath.pi(Scalar.mathContext))
        assertAtanEquals(-BigDecimalMath.pi(Scalar.mathContext))
    }

    @Test
    fun cos() {
        assertCosEquals(BigDecimal("7.7"))
        assertCosEquals(BigDecimal("-7.7"))
        assertCosEquals(BigDecimalMath.pi(Scalar.mathContext))
        assertCosEquals(-BigDecimalMath.pi(Scalar.mathContext))
    }

    @Test
    fun sin() {
        assertSinEquals(BigDecimal("7.7"))
        assertSinEquals(BigDecimal("-7.7"))
        assertSinEquals(BigDecimalMath.pi(Scalar.mathContext))
        assertSinEquals(-BigDecimalMath.pi(Scalar.mathContext))
    }

    @Test
    fun sqrt() {
        assertSqrtEquals(BigDecimal("7.7"))
        assertSqrtEquals(BigDecimalMath.pi(Scalar.mathContext))
    }

    @Test
    fun getDrawableValue() {
        assertEquals(5341, Scalar("5.3419", "6").getDrawableValue(3))
    }

    @Test
    fun getScale() {
        assertEquals(1, Scalar("2.5", "1").getScale())
        assertEquals(2, Scalar("2.5", "2").getScale())
        assertEquals(-2, Scalar("2.5", "-2").getScale())
        assertEquals(0, Scalar("2.5").getScale())
        assertEquals(3, Scalar("1234").getScale())
        assertEquals(1, Scalar("12.34").getScale())
        assertEquals(-6, Scalar("0.0000025").getScale())
        assertEquals(-6, Scalar("0.00000025", "1").getScale())
    }

    @Test
    fun testEquals() {
        assertEquals(Scalar("2.5", "-6"), Scalar("0.0000025"))
        assertEquals(Scalar("2.5", "1"), Scalar("25"))
        assertEquals(Scalar("2.5000", "1"), Scalar("25"))
        assertEquals(Scalar("0.0135"), Scalar("135", "-4"))
        assertEquals(Scalar("0.01350000"), Scalar("135", "-4"))
    }

    @Test
    fun compareTo() {
        assertTrue(Scalar("3.4") < Scalar("25"))
        assertTrue(Scalar("3.4", "1") > Scalar("25"))
        assertTrue(Scalar("0.135") > Scalar("135", "-4"))
        assertTrue(Scalar("0.135") > Scalar("-135", "4"))
    }

    private fun assertAtanEquals(expected: BigDecimal) =
        assertEquals(
            Scalar(BigDecimalMath.atan(expected, Scalar.mathContext).toString()),
            Scalar(expected.toString()).atan()
        )

    private fun assertCosEquals(expected: BigDecimal) =
        assertEquals(
            Scalar(BigDecimalMath.cos(expected, Scalar.mathContext).toString()),
            Scalar(expected.toString()).cos()
        )

    private fun assertSinEquals(expected: BigDecimal) =
        assertEquals(
            Scalar(BigDecimalMath.cos(expected, Scalar.mathContext).toString()),
            Scalar(expected.toString()).cos()
        )

    private fun assertSqrtEquals(expected: BigDecimal) =
        assertEquals(
            Scalar(BigDecimalMath.sqrt(expected, Scalar.mathContext).toString()),
            Scalar(expected.toString()).sqrt()
        )
}