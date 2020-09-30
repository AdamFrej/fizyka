package affinePhysics


infix fun Velocity.times(dt: Time): Delta<Position> =
    Delta(position, Position(position.getVector() plus (getVector() over dt.value)))

infix fun Delta<Position>.over(dt: Time) = Velocity(getVector() over dt.value, b)

//infix fun Acceleration.times(dt: Time) = Delta<Velocity>(vector times dt.value)
//infix fun Delta<Velocity>.over(dt: Time) = Delta<Velocity>(vector over dt.value)

//infix fun Acceleration.times(m: Mass) = Force(vector times m.value)
infix fun Force.over(m: Mass) = Acceleration(vector over m.value)