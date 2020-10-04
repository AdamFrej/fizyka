package affinePhysics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun distance() {
        val pos1 = Position("12", "32")
        val pos2 = Position("49", "37")
        val distance = (pos1 minus pos2).getVector().getValue()
        val reverse = (pos2 minus pos1).getVector().getValue()
        assertEquals(distance, reverse, "distance: %s, reverse: %s".format(distance, reverse))
    }
}