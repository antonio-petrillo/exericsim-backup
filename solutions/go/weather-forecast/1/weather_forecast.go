// Package weather report report the weather.
package weather

// CurrentCondition inidicate the current weather condition.
var CurrentCondition string

// CurrentLocation indicate the current location.
var CurrentLocation string

// Forecast report the weather.
func Forecast(city, condition string) string {
	CurrentLocation, CurrentCondition = city, condition
	return CurrentLocation + " - current weather condition: " + CurrentCondition
}
