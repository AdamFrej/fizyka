import affinePhysics.Force
import affinePhysics.ForceFunction
import affinemaths.Scalar

class RestoringForce: ForceFunction<Ball>() {
    override fun compute(body1: Ball, body2: Ball): Force {
        val distance = body1.p.distance(body2.p)
        val direction = (body2.p minus body1.p).vector
        var value = Scalar.ZERO
        return Force(value, direction)
    }
}