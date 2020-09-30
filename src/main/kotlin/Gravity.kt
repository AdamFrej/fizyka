import affinePhysics.Force
import affinePhysics.ForceFunction
import affinemaths.Scalar

class Gravity : ForceFunction<Ball>() {
    private val G = Scalar("0.000000000066740831")
    override fun compute(body1: Ball, body2: Ball): Force {
        return Force("1", "1")
//        val distance = body1.p.distance(body2.p)
//        var value = G.times(body1.m.value).times(body2.m.value).over(distance.pow("2"))
//        if (distance < body1.r) value = value times (distance over body1.r).pow("2")
//        if (distance < body2.r) value = value times (distance over body2.r).pow("2")
//        val direction = (body2.p minus body1.p).vector
//        return Force(value, direction)
    }
}