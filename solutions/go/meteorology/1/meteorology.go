package meteorology

import "fmt"

type TemperatureUnit int

const (
	Celsius    TemperatureUnit = 0
	Fahrenheit TemperatureUnit = 1
)


func (tu TemperatureUnit) String() string {
    if tu == Celsius {
        return "°C"
    } else {
        return "°F"
    }
}

type Temperature struct {
	degree int
	unit   TemperatureUnit
}

func (t Temperature) String() string {
    return fmt.Sprintf("%d %s", t.degree, t.unit)
}

type SpeedUnit int

const (
	KmPerHour    SpeedUnit = 0
	MilesPerHour SpeedUnit = 1
)

func (su SpeedUnit) String() string {
    if su == KmPerHour {
        return "km/h"
    } else {
        return "mph"
    }
}

type Speed struct {
	magnitude int
	unit      SpeedUnit
}

func (s Speed) String() string {
    return fmt.Sprintf("%d %s", s.magnitude, s.unit)
}

type MeteorologyData struct {
	location      string
	temperature   Temperature
	windDirection string
	windSpeed     Speed
	humidity      int
}

func (md MeteorologyData) String() string {
    return fmt.Sprintf("%s: %s, Wind %s at %s, %d%% Humidity",
                       md.location, 
                       md.temperature,
                       md.windDirection,
                       md.windSpeed,
                       md.humidity)
}
