import affinePhysics.*
import affinemaths.Vector

class Ball(var v: Velocity, var p: Position, var m: Mass, var r: Length) {
    var f = Force(Vector.ZERO)

    fun update(f:Force) {
        this.f = this.f plus f
    }
    fun update(t: Time) {
        v = v plus ((f over m) times t)
        println("V X: " + v.vector.getDrawableX(0) + ", Y: " + v.vector.getDrawableY(0))
        p = p plus (v times t)
        f = Force(Vector.ZERO)
    }

    fun getX(scale:Int): Int {
        return p.point.getDrawableX(scale)
    }

    fun getY(scale:Int): Int {
        return p.point.getDrawableY(scale)
    }
}