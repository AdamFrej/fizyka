package affinePhysics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScalarTest {

    @Test
    fun minusTest() {
        val time1 = Scalar<Time>("13")
        val time2 = Scalar<Time>("10")
        val time11 = Scalar<Time>("13")
        assertEquals(Scalar.Delta<Time>("3"), time1 minus time2)
        assertEquals(Scalar.Delta<Time>("-3"), time2 minus time1)
        assertEquals(time1, time11)
    }
}