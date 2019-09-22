import maths.*
import java.awt.Color

class Ball(var v: Velocity, var position: Position, private var m: Mass) : Drawable {
    var f:Force = Force(0.0,0.0)

    fun update(force: Force, t:Time) {
        f = force
        v = v plus ((f over m) times t)
        position = position plus (v times t)
    }

    override fun getX(): Int {
        return position.getX()
    }

    override fun getY(): Int {
        return position.getY()
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