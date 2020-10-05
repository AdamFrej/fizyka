package affinePhysics

import affinePhysics.Scalar.Delta as ScalarDelta
import affinePhysics.Vector.Delta as VectorDelta

class Position
class Velocity
class Acceleration
class Force

class Time
class Mass

infix fun Vector<Velocity>.times(dt: ScalarDelta<Time>) = VectorDelta<Position>(vector times dt.scalar, origin)

infix fun VectorDelta<Position>.over(dt: ScalarDelta<Time>) = Vector<Velocity>(vector over dt.scalar, origin)

@JvmName("timesAcceleration")
infix fun Vector<Acceleration>.times(dt: ScalarDelta<Time>) = VectorDelta<Velocity>(vector times dt.scalar, origin)

@JvmName("overVelocity")
infix fun VectorDelta<Velocity>.over(dt: ScalarDelta<Time>) = Vector<Acceleration>(vector over dt.scalar, origin)


infix fun Vector<Acceleration>.times(m: Scalar<Mass>) = Vector<Force>(vector times m.scalar, origin)
infix fun Vector<Force>.over(m: Scalar<Mass>) = Vector<Acceleration>(vector over m.scalar, origin)