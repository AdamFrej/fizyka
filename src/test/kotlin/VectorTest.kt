import maths.Angle
import maths.Scalar
import maths.Vector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class VectorTest {
    private fun assertEquals(expected: Scalar, actual: Scalar) =
        assertTrue(
            (actual minus expected) < Scalar("0.0000000001"),
            "expected: %s, actual: %s".format(expected, actual)
        )

    private fun assertEquals(expected: Angle, actual: Angle) =
        assertTrue((actual minus expected) < Angle("0.000000001"), "expected: %s, actual: %s".format(expected, actual))

    @Test
    fun getXYTest() {
        assertEquals(10, Vector("10", "0").getX().toInt())
        assertEquals(0, Vector("10", "0").getY().toInt())

        assertEquals(0, Vector("10", Angle.PI.times("0.5")).getX().toInt())
        assertEquals(10, Vector("10", Angle.PI.times("0.5")).getY().toInt())

        assertEquals(-10, Vector("10", Angle.PI).getX().toInt())
        assertEquals(0, Vector("10", Angle.PI).getY().toInt())

        assertEquals(0, Vector("10", Angle.PI.times("1.5")).getX().toInt())
        assertEquals(-10, Vector("10", Angle.PI.times("1.5")).getY().toInt())

        assertEquals(10, Vector("10", Angle.PI.times("2")).getX().toInt())
        assertEquals(0, Vector("10", Angle.PI.times("2")).getY().toInt())
    }

    @Test
    fun inverseTest() {
        assertEquals(Angle.PI, Vector("10", "0").inverse().angle)
        assertEquals(Scalar("10"), Vector("10", "0").inverse().value)
        assertEquals(Vector("9", "0").getX(), -Vector("9", "0").inverse().getX())
        assertEquals(Vector("9", Angle.PI.times("0.5")).getY(), -Vector("9", Angle.PI.times("0.5")).inverse().getY())
        assertEquals(Angle.PI.times("1.5"), Vector("10", Angle.PI.times("0.5")).inverse().angle)
        assertEquals(Angle("0"), Vector("10", Angle.PI).inverse().angle)
        assertEquals(Angle.PI.times("0.5"), Vector("10", Angle.PI.times("1.5")).inverse().angle)
        assertEquals(Angle.PI, Vector("10", Angle.PI.times("2")).inverse().angle)
    }

    @Test
    fun plusTest() {
        val vector1 = Vector("3", "0")
        val vector2 = Vector("4", Angle.PI.times("0.5"))
        assertEquals((vector2 plus vector1).value, (vector1 plus vector2).value)
        assertEquals((vector2 plus vector1).angle, (vector1 plus vector2).angle)
        assertEquals(Scalar("5"), (vector1 plus vector2).value)
        assertEquals(Scalar("5"), (vector2 plus vector1).value)
        val vector3 = Vector("9", Angle.PI)
        val vector4 = Vector("9", Angle.PI.times("1.5"))
        assertEquals(Angle.PI.times("1.25"), (vector3 plus vector4).angle)
    }

    @Test
    fun minusTest() {
        val vector1 = Vector("7.071067811865475", "0")
        val vector2 = Vector("7.071067811865475", Angle.PI.times("0.75"))
        assertEquals(Angle.PI.times("0.875"), (vector2 minus vector1).angle)
        assertEquals(Angle.PI.times("1.875"), (vector1 minus vector2).angle)
        assertEquals(Scalar("13.06562965"), (vector1 minus vector2).value)
        assertEquals(Scalar("13.06562965"), (vector2 minus vector1).value)
    }
}