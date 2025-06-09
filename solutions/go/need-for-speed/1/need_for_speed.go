package speed

type Car struct {
	battery      int
	batteryDrain int
	speed        int
	distance     int
}

func NewCar(speed, batteryDrain int) Car {
	var c Car
	c.battery = 100
	c.batteryDrain = batteryDrain
	c.speed = speed
	return c
}

type Track struct {
	distance int
}

func NewTrack(distance int) Track {
	var t Track
	t.distance = distance
	return t
}

func Drive(car Car) Car {
	battery := car.battery - car.batteryDrain
	if battery >= 0 {
		car.battery -= car.batteryDrain
		car.distance += car.speed
	}
	return car
}

func CanFinish(car Car, track Track) bool {
	cycles := car.battery / car.batteryDrain
	return car.speed*cycles >= track.distance
}
