package affinePhysics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VectorTest {
    @Test
    fun minusTest() {
        val origin = affinemaths.Vector("0", "0")
        val one = affinemaths.Vector("1", "1")
        val three = affinemaths.Vector("3", "3")

        val pos1 = Vector<Position>(one, origin)
        val pos2 = Vector<Position>(three, origin)
        val pos3 = Vector<Position>(three, origin)

        val delta = pos2 minus pos1
        val reverseDelta = pos1 minus pos2

        assertEquals(Vector.Delta<Position>(affinemaths.Vector("2","2"), one), delta)
        assertEquals(Vector.Delta<Position>(affinemaths.Vector("-2","-2"), three), reverseDelta)

        assertEquals(pos2,pos3)
    }
    @Test
    fun plusTest() {
        val origin = affinemaths.Vector("0", "0")
        val one = affinemaths.Vector("1", "1")
        val three = affinemaths.Vector("3", "-3")

        val pos1 = Vector<Position>(one, origin)
        val delta = Vector.Delta<Position>(three, one)
        val pos2 = pos1 plus delta

        assertEquals(Vector<Position>(affinemaths.Vector("4","-2"), origin), pos2)
    }
}