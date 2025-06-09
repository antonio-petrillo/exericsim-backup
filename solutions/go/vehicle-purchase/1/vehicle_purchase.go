package purchase

import "fmt"

func NeedsLicense(kind string) bool {
	needLicense := false

	switch kind {
	case "car":
		needLicense = true
	case "truck":
		needLicense = true
	}

	return needLicense
}

func ChooseVehicle(option1, option2 string) string {
	choice := ""
	if option1 < option2 {
		choice = option1
	} else {
		choice = option2
	}
	return fmt.Sprintf("%s is clearly the better choice.", choice)
}

func CalculateResellPrice(originalPrice, age float64) float64 {
	if age < 3 {
		return originalPrice * 4 / 5
	} else if age >= 10 {
		return originalPrice / 2
	} else {
		return originalPrice * 70 / 100
	}
}
