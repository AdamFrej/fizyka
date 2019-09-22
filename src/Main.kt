import maths.*
import java.awt.*
import java.awt.Dimension
import java.util.concurrent.*
import javax.swing.*

class Main(private val wid: Int, private val hei: Int) : JPanel(), Runnable {
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
            if(balls != null) balls.forEach { b: Ball -> drawBall(b) }
            drawLine(0, 600, 1000, 600)
        }
    }

    private fun Graphics.drawBall(drawable: Drawable): Unit {
        val oldColor = color
        color = drawable.getColor()
        fillOval(drawable.getX()+300, drawable.getY()+300, drawable.getWidth(), drawable.getHeight())
        color = oldColor
    }

    private fun Graphics.drawGrid() {
        color = Color.WHITE
        fillRect(0, 0, width, height)
        color = Color.LIGHT_GRAY
        for (i in 1..30) drawOval(300 - i * 25, 300 - i * 25, i * 50, i * 50)
        drawLine(300, 0, 300, 1000)
        drawLine(0, 300, 1000, 300)
    }

    override fun run() {
        balls = balls.map {b:Ball->b.update(F_g, dt)}
        repaint()
    }

    override fun getPreferredSize() = Dimension(wid, hei)

    private val F_g = Force(9.81,Math.PI/2)
    private val dt = Time(0.1)
    private var balls = listOf(Ball(Position(112.0,-Math.PI/6), Mass(1.0)))
}

fun main(a: Array<String>) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(Main(600, 600), 0, 1, TimeUnit.MILLISECONDS)
}
