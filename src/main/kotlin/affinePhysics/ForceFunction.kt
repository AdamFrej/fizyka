package affinePhysics

abstract class ForceFunction<B> {
    abstract fun compute(body1: B, body2: B): Force
}