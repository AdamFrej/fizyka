import maths.*
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JFrame
import javax.swing.JPanel

class Main(private val size: Int) : JPanel(), Runnable {
    init {
        val f = JFrame("Fizyka co umyka")
        f.add(this)
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.pack()
        f.isVisible = true
        isDoubleBuffered = true
    }

    override fun paint(g: Graphics) {
        with(g) {
            drawGrid()
            if (balls != null) balls.forEach { b: Ball -> drawBall(b) }
        }
    }

    private fun Graphics.drawVector(vector: Vector, ox: Int, oy: Int, clr: Color) {
        val oldColor = color
        color = clr
        val vx = ox + vector.getX().number.toInt()
        val vy = oy + vector.getY().number.toInt()
        drawLine(ox, oy, vx, vy)
        fillOval(vx - 2, vy - 2, 4, 4)
        color = oldColor
    }

    private fun Graphics.drawBall(drawable: Drawable) {
        val oldColor = color
        color = drawable.getColor()
        fillOval(
            drawable.getX() + size - drawable.getWidth() / 2,
            drawable.getY() + size - drawable.getHeight() / 2,
            drawable.getWidth(),
            drawable.getHeight()
        )
        color = oldColor
    }

    private fun Graphics.drawGrid() {
        color = Color.WHITE
        fillRect(0, 0, width, height)
        color = Color.LIGHT_GRAY
        for (i in 1..20) drawOval(size - i * size / 20, size - i * size / 20, i * size / 10, i * size / 10)
        drawLine(size, 0, size, size * 2)
        drawLine(0, size, size * 2, size)
    }

    override fun run() {
        balls.forEach { b: Ball -> b.update(Force(b.r.vector.inverse().copyAngle(Scalar(9.81))), dt) }
        repaint()
    }

    override fun getPreferredSize() = Dimension(size * 2, size * 2)

    private val dt = Time(0.1)
    private var balls = listOf(Ball(Velocity(10.0, 2.0), Position(112.0, Math.PI / 6), Mass(1.0)))
}

fun main(a: Array<String>) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(Main(500), 0, 10, TimeUnit.MILLISECONDS)
}
