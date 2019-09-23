package maths

infix fun Position.minus(position: Position) = DeltaPosition(vector minus position.vector)
infix fun Position.plus(deltaPosition: DeltaPosition) = Position(vector plus deltaPosition.vector)
infix fun DeltaPosition.plus(position: Position) = DeltaPosition(vector plus position.vector)

infix fun Velocity.minus(velocity: Velocity) = DeltaVelocity(vector minus velocity.vector)
infix fun Velocity.plus(deltaVelocity: DeltaVelocity) = Velocity(vector plus deltaVelocity.vector)
infix fun DeltaVelocity.plus(velocity: Velocity) = Velocity(vector plus velocity.vector)

infix fun DeltaPosition.over(time: Time) = Velocity(vector over time.value)
infix fun Velocity.times(time: Time) = DeltaPosition(vector times time.value)
infix fun Time.times(velocity: Velocity) = velocity times this

infix fun DeltaVelocity.over(time: Time) = Acceleration(vector over time.value)
infix fun Acceleration.times(time: Time) = DeltaVelocity(vector times time.value)
infix fun Time.times(acceleration: Acceleration) = acceleration times this

infix fun Acceleration.times(mass: Mass) = Force(vector times mass.value)
infix fun Mass.times(acceleration: Acceleration) = acceleration times this
infix fun Force.over(mass: Mass) = Acceleration(vector over mass.value)