package affinePhysics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PhysicsTest {
    private val origin = affinemaths.Vector("0", "0")
    private val pos1 = Vector<Position>(affinemaths.Vector("1230", "-320"), origin)
    private val pos2 = Vector<Position>(affinemaths.Vector("1330", "-370"), origin)
    private val pos3 = Vector<Position>(affinemaths.Vector("1530", "-470"), origin)
    private val time1 = Scalar<Time>("121")
    private val time2 = Scalar<Time>("141")
    private val time3 = Scalar<Time>("161")

    @Test
    @DisplayName("Verify that velocity is change in position over time")
    fun velocityDefinition() {
        val dt = time2 minus time1
        val vel = (pos2 minus pos1) over dt

        assertEquals(Vector<Velocity>(affinemaths.Vector("5", "-2.5"), pos1.vector), vel)
    }


    @Test
    @DisplayName("Verify that position can be derived from earlier position, given velocity and time")
    fun positionFromVelocity() {
        val vel = Vector<Velocity>(affinemaths.Vector("13", "-6.5"), pos1.vector)

        val dt = time3 minus time1

        val delta = vel times dt
        val pos2 = pos1 plus delta

        assertEquals(Vector<Position>(affinemaths.Vector("1750", "-580"), origin), pos2)
    }

    @Test
    @DisplayName("Verify that acceleration is change in velocity over time")
    fun accelerationTest() {
        val dt1 = time2 minus time1
        val dt2 = time3 minus time2
        val vel1 = (pos2 minus pos1) over dt1
        val vel2 = (pos3 minus pos2) over dt2

        val acc = (vel2 minus vel1) over  (time3 minus time2)

        assertEquals(Vector<Acceleration>(affinemaths.Vector("0.25","-0.125"), vel1.origin plus vel1.vector), acc)
    }

    @Test
    fun forceTest() {

    }
}