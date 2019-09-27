import affinePhysics.*
import affinemaths.Vector
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JFrame
import javax.swing.JPanel

class Main(private val size: Int, private val drawVectors: String, private val options: String) : JPanel(), Runnable {
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

    private fun Graphics.drawVector(vector: Vector, ox: Int, oy: Int, clr: Color, scale: Int) {
        val oldColor = color
        color = clr
        val vx = ox + vector.getDrawableX(scale)
        val vy = oy + vector.getDrawableY(scale)
        drawLine(ox, oy, vx, vy)
        fillOval(vx - 3, vy - 3, 6, 6)
        color = oldColor
    }

    private fun Graphics.drawBall(ball: Ball) {
        val oldColor = color
        color = Color.BLACK
        val rScale = normalizeScale(ball.r.value.getScale())
        val radius = ball.r.value.getDrawableValue(rScale) + 14

        if (fill) fillOval(ball.getX(6) + size - radius / 2, ball.getY(6) + size - radius / 2, radius, radius)
        else drawOval(ball.getX(6) + size - radius / 2, ball.getY(6) + size - radius / 2, radius, radius)

        if (drawR) drawVector((ball.p minus o).vector, size, size, Color.GREEN, 2)
        if (drawV) drawVector(ball.v.vector, ball.getX(6) + size, ball.getY(6) + size, Color.BLUE, 1)
        if (drawF) drawVector(ball.f.vector, ball.getX(6) + size, ball.getY(6) + size, Color.RED, 19)
        color = oldColor
    }

    private fun normalizeScale(scale: Int): Int {
        return (scale / 3) * 3
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
        balls.forEach { b: Ball -> b.update(dt) }
        balls.forEach { ball: Ball ->
            balls.forEach { otherBall: Ball ->
                if (ball != otherBall) {
                    ball.update(gravity.compute(ball, otherBall))
                }
            }
        }
        balls.forEach { b: Ball -> println("X: " + b.getX(6) + ", Y: " + b.getY(6)) }
        repaint()
    }

    override fun getPreferredSize() = Dimension(size * 2, size * 2)

    private val drawR = drawVectors.contains("r")
    private val drawV = drawVectors.contains("v")
    private val drawF = drawVectors.contains("f")
    private val fill = options.contains("f")

    private val gravity = Gravity()
    private val o = Position("0", "0")
    private val mz = Mass("5972190000000000000000000")
    private val rz = Length("6371008")
    private val mk = Mass("73476730000000000000000")
    private val rk = Length("1737000")
    private val earth = Ball(Velocity("0", "0"), Position("0", "0"), mz, rz)
    private val moon = Ball(Velocity("0", "-1022"), Position("384400000", "0"), mk, rk)
    private val antimoon = Ball(Velocity("0", "1022"), Position("-384400000", "0"), mk, rk)
    private val dt = Time("1000")
    private var balls = listOf(earth, moon, antimoon)
}

fun main(a: Array<String>) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(Main(500, "vf", ""), 0, 10, TimeUnit.MILLISECONDS)
}
