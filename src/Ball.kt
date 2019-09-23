import maths.*
import java.awt.Color

class Ball(var v: Velocity, var r: Position, var m: Mass) : Drawable {
    var f:Force = Force(0.0,0.0)

    fun update(force: Force, t:Time) {
        f = force
        v = v plus ((f over m) times t)
        r = r plus (v times t)
    }

    override fun getX(): Int {
        return r.getX()
    }

    override fun getY(): Int {
        return r.getY()
    }

    override fun getColor(): Color {
        return Color.BLACK
    }

    override fun getWidth(): Int {
        return 14
    }

    override fun getHeight(): Int {
        return 14
    }
}