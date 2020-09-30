import affinePhysics.Delta
import affinePhysics.Position
import affinePhysics.Time
import affinePhysics.Velocity
import affinemaths.Scalar
import affinemaths.Vector
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.random.Random

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
            drawPosition(positionA)
            drawPosition(positionB)
            drawPosition(positionC)
            drawDelta(delta)
//            drawVelocity(vel)
        }
    }

    private fun Graphics.drawLine(x1: Int, y1: Int, x2: Int, y2: Int, color: Color? = Color.BLACK) {
        val oldColor = this.color
        this.color = color
        drawLine(size + x1, size - y1, size + x2, size - y2)
        this.color = oldColor
    }

    private fun Graphics.drawPosition(position: Position) {
        drawVector(position.getVector(), Color.GREEN)
    }

    private fun Graphics.drawDelta(delta: Delta<Position>) {
        drawVectorFrom(delta.a.getVector(), delta.b.getVector(), Color.RED)
    }

    private fun Graphics.drawVelocity(velocity: Velocity) {
        drawVectorFrom(
            velocity.position.getVector() plus velocity.getVector(),
            velocity.position.getVector(),
            Color.BLACK
        )
    }

    private fun Graphics.drawVector(vector: Vector, color: Color? = Color.BLACK) {
        drawVectorFrom(vector, Vector("0", "0"), color)
    }

    private fun Graphics.drawVectorFrom(vector: Vector, origin: Vector, color: Color? = Color.BLACK) {
        val vx = vector.x.toInt()
        val vy = vector.y.toInt()
        drawLine(origin.x.toInt(), origin.y.toInt(), vx, vy, color)
        drawLine(vx - 10, vy, vx, vy, color)
        drawLine(vx, vy - 10, vx, vy, color)
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
        repaint()
    }

    private fun randomScalar(exponent: String) = Scalar(Random.nextInt(-100, 100).toString(), exponent)

    override fun getPreferredSize() = Dimension(size * 2, size * 2)

    //    private val drawR = drawVectors.contains("r")
//    private val drawV = drawVectors.contains("v")
//    private val drawF = drawVectors.contains("f")
//    private val fill = options.contains("f")
//
//    private val gravity = Gravity()
//    private val o = Position("0", "0")
//    private val mz = Mass("5972190000000000000000000")
//    private val rz = Length("6371008")
////    private val earth = Ball(Velocity("0", "0"), Position("0", "0"), mz, rz)
//    private val dt = Time("100")
    private val positionA = Position("100", "-100")
    private val positionB = Position("120", "-100")
    private val deltaAB = positionB minus positionA
    private val timeA = Time("1")
    private val timeB = Time("2")
    private val deltaT = timeB minus timeA
    private val vel = Velocity(Vector("0", "-30"), positionB)
    private val positionC = Position(positionB.getVector() plus (vel.getVector() over deltaT.value))
    private val delta = Delta(positionB, positionC)

}

fun main(a: Array<String>) {
    val executor = Executors.newSingleThreadScheduledExecutor()
    executor.scheduleAtFixedRate(Main(500, "", ""), 0, 10, TimeUnit.MILLISECONDS)
}
