import maths.*
import java.awt.Color

class Ball(private val position: Position, private val m: Mass) : Drawable {
    var v: Velocity = Velocity(0.0,0.0)

    fun update(f: Force, t:Time): Ball {
        v = v plus ((f over m) times t)
        return Ball(position plus (v times t), m)
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