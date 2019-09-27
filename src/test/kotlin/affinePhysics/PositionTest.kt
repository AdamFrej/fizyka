package affinePhysics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun distance() {
        val pos1 = Position("12", "32")
        val pos2 = Position("49", "37")
        val distance = pos1.distance(pos2)
        val reverse = pos2.distance(pos1)
        assertEquals(distance, reverse, "distance: %s, reverse: %s".format(distance, reverse))
    }
}