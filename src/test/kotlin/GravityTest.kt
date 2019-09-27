import affinePhysics.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GravityTest {

    @Test
    fun compute() {
        val mz = Mass("5972190000000000000000000")
        val rz = Length("6371008")
        val m = Mass("1")
        val r = Length("1")
        val earth = Ball(Velocity("0", "0"), Position("0", "0"), mz, rz)
        val smallBall = Ball(Velocity("0", "0"), Position("6371008", "0"), m, r)

        val gravity = Gravity()

        assertEquals(Force("9.819942050821504707761360425352541408191", "0"), gravity.compute(earth, smallBall))
    }
}