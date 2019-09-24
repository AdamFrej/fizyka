import maths.*
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.math.MathContext
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JFrame
import javax.swing.JPanel

class Main(private val size: Int, private val drawVectors: String) : JPanel(), Runnable {
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
        val vx = ox + vector.getX().toInt()
        val vy = oy + vector.rotate(Angle.PI).getY().toInt()
        drawLine(ox, oy, vx, vy)
        fillOval(vx - 3, vy - 3, 6, 6)
        color = oldColor
    }

    private fun Graphics.drawBall(ball: Ball) {
        val oldColor = color
        color = Color.BLACK
        fillOval(
            ball.getX() + size - 14 / 2,
            ball.getY() + size - 14 / 2,
            14,
            14
        )
        if (drawR) drawVector(ball.r.vector, size, size, Color.GREEN)
        if (drawV) drawVector(ball.v.vector, ball.getX() + size, ball.getY() + size, Color.BLUE)
        if (drawF) drawVector(ball.f.vector, ball.getX() + size, ball.getY() + size, Color.RED)
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
        balls.forEach { b: Ball -> b.update(Force(b.r.vector.inverse().copyAngle(Scalar("9.81"))), dt) }
        repaint()
    }

    override fun getPreferredSize() = Dimension(size * 2, size * 2)

    private val context = MathContext(10)
    private val drawR = drawVectors.contains("r")
    private val drawV = drawVectors.contains("v")
    private val drawF = drawVectors.contains("f")
    private val dt = Time("0.01")
    private var balls = listOf(Ball(Velocity("10", "2"), Position("112", Angle.PI.over("6")), Mass("1.0")))
}

fun main(a: Array<String>) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(Main(500, ""), 0, 10, TimeUnit.MILLISECONDS)
}
