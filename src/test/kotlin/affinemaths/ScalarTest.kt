package affinemaths

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

internal class ScalarTest {

    @Test
    fun toDouble(){
        assertEquals(7.7, Scalar("77", "-1").toDouble())
        assertEquals(0.77, Scalar("77", "-2").toDouble())
        assertEquals(77.0, Scalar("77").toDouble())
        assertEquals(770000.0, Scalar("77", "4").toDouble())
        assertEquals(0.000077, Scalar("77", "-6").toDouble())
    }

    @Test
    fun pow() {
        assertEquals(Scalar("25"), Scalar("5").pow("2"))
        assertEquals(Scalar("27"), Scalar("3").pow("3"))
        assertEquals(Scalar("121", "8"), Scalar("11", "4").pow("2"))
        assertEquals(Scalar("1"), Scalar("11", "4").pow("0"))
        assertEquals(Scalar("11", "4"), Scalar("11", "4").pow("1"))
    }

    @Test
    fun plus() {
        assertEquals(Scalar("25"), Scalar("20") plus Scalar("5"))
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
        assertEquals(Scalar("653.87"), Scalar("6.54", "2") minus Scalar("1.23", "-1"))
    }

    @Test
    fun times() {
        assertEquals(Scalar("0"), Scalar("25") times Scalar.ZERO)
        assertEquals(Scalar("0"), Scalar.ZERO times Scalar("25"))
        assertEquals(Scalar("100"), Scalar("5") times Scalar("20"))
        assertEquals(Scalar("-100"), Scalar("5") times Scalar("-20"))
        assertEquals(Scalar("99"), Scalar("-3") times Scalar("-33"))
        assertEquals(Scalar("15", "7"), Scalar("5", "3") times Scalar("3", "4"))
        assertEquals(Scalar("15", "-1"), Scalar("5", "3") times Scalar("3", "-4"))
    }

    @Test
    fun over() {
        assertEquals(Scalar("0"), Scalar.ZERO over Scalar("25"))
        assertEquals(Scalar("34567", "-3") times Scalar("25", "-2"), Scalar("34567", "-3") over Scalar("4"))
        assertEquals(Scalar("25", "-2"), Scalar("5") over Scalar("20"))
        assertEquals(Scalar("-25", "-2"), Scalar("5") over Scalar("-20"))
        assertEquals(Scalar("3"), Scalar("-99") over Scalar("-33"))
        assertEquals(Scalar("25", "-2"), Scalar("5", "3") over Scalar("2", "4"))
        assertEquals(Scalar("125", "5"), Scalar("5", "3") over Scalar("4", "-4"))
    }

    @Test
    fun atan() {
        assertAtanEquals(7.7, Scalar("77", "-1"))
        assertAtanEquals(-7.7, Scalar("-77", "-1"))
        assertAtanEquals(3.14, Scalar("314", "-2"))
        assertAtanEquals(-3.14, Scalar("-314", "-2"))
    }

    @Test
    fun cos() {
        assertCosEquals(7.7, Scalar("77", "-1"))
        assertCosEquals(-7.7, Scalar("-77", "-1"))
        assertCosEquals(3.14, Scalar("314", "-2"))
        assertCosEquals(-3.14, Scalar("314", "-2"))
    }

    @Test
    fun sin() {
        assertSinEquals(7.7, Scalar("77", "-1"))
        assertSinEquals(-7.7, Scalar("-77", "-1"))
        assertSinEquals(3.14, Scalar("314", "-2"))
        assertSinEquals(-3.14, Scalar("-314", "-2"))
    }

    @Test
    fun sqrt() {
        assertSqrtEquals(7.7, Scalar("77", "-1"))
        assertSqrtEquals(3.14, Scalar("314", "-2"))
    }

    @Test
    fun getDrawableValue() {
        assertEquals(5341, Scalar("5.3419", "6").getDrawableValue(3))
    }

    @Test
    fun getScale() {
        assertEquals(1, Scalar("25").getScale())
        assertEquals(2, Scalar("25", "1").getScale())
        assertEquals(-2, Scalar("25", "-3").getScale())
        assertEquals(0, Scalar("25","-1").getScale())
        assertEquals(3, Scalar("1234").getScale())
        assertEquals(1, Scalar("1234","-2").getScale())
        assertEquals(-6, Scalar("25", "-7").getScale())
    }

    @Test
    fun testEquals() {
        assertEquals(Scalar("25", "-5"), Scalar("0.00025"))
        assertEquals(Scalar("25", "1"), Scalar("250"))
        assertEquals(Scalar("25", "0"), Scalar("25"))
        assertEquals(Scalar("2.5000", "1"), Scalar("25"))
        assertEquals(Scalar("0.0135"), Scalar("135", "-4"))
        assertEquals(Scalar("0.01350000"), Scalar("135", "-4"))
    }

    @Test
    fun compareTo() {
        assertTrue(Scalar("34", "-1") < Scalar("25"))
        assertTrue(Scalar("34") > Scalar("25"))
        assertTrue(Scalar("135", "-3") > Scalar("135", "-4"))
        assertTrue(Scalar("135", "-3") > Scalar("-135", "4"))
    }

    private fun assertAtanEquals(expected: Double, actual: Scalar) =
        assertEquals((atan(expected) * 10_000).toInt(), (actual.atan().toDouble() * 10_000).toInt())

    private fun assertCosEquals(expected: Double, actual: Scalar) =
        assertEquals((cos(expected) * 10_000).toInt(), (actual.cos().toDouble() * 10_000).toInt())

    private fun assertSinEquals(expected: Double, actual: Scalar) =
        assertEquals((sin(expected) * 10_000).toInt(), (actual.sin().toDouble() * 10_000).toInt())

    private fun assertSqrtEquals(expected: Double, actual: Scalar) =
        assertEquals((sqrt(expected) * 10_000).toInt(), (actual.sqrt().toDouble() * 10_000).toInt())
}