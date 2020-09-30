import affinePhysics.*
import affinemaths.Vector

class Ball(var v: Velocity, var p: Position, var m: Mass, var r: Length) {
    var f = Force(Vector.ZERO)

    fun update(f: Force) {
        this.f = this.f plus f
    }

    fun update(t: Time) {
//        v = v plus ((f over m) times t)
//        p = p plus (v times t)
        f = Force(Vector.ZERO)
    }
}