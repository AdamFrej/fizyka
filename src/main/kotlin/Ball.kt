import maths.*

class Ball(var v: Velocity, var r: Position, var m: Mass) {
    var f: Force = Force("0", "0")

    fun update(force: Force, t: Time) {
        f = force
        v = v plus ((f over m) times t)
        r = r plus (v times t)
    }

    fun getX(): Int {
        return r.vector.getX().toInt()
    }

    fun getY(): Int {
        return r.vector.getY().toInt()
    }
}