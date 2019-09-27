import affinePhysics.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BallTest {

    @Test
    fun update() {
        val b = Ball(Velocity("0", "0"), Position("10", "0"), Mass("1"), Length("1"))
        b.update(Force("10", "0"))
        b.update(Time("1"))
        assertEquals(Velocity("10", "0"), b.v)
        assertEquals(Position("20", "0"), b.p)
        b.update(Time("1"))
        assertEquals(Velocity("10", "0"), b.v)
        assertEquals(Position("30", "0"), b.p)
        b.update(Force("10", "0"))
        b.update(Time("1"))
        assertEquals(Velocity("20", "0"), b.v)
        assertEquals(Position("50", "0"), b.p)
        b.update(Force("0", "10"))
        b.update(Time("1"))
        assertEquals(Velocity("20", "10"), b.v)
        assertEquals(Position("70", "10"), b.p)
        b.update(Force("-10", "10"))
        b.update(Time("1"))
        assertEquals(Velocity("10", "20"), b.v)
        assertEquals(Position("80", "30"), b.p)
        b.update(Force("-10", "-20"))
        b.update(Time("1"))
        assertEquals(Velocity("0", "0"), b.v)
        assertEquals(Position("80", "30"), b.p)
    }
}