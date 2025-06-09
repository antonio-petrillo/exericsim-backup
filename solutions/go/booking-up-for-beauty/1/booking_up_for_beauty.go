package booking

import (
	"fmt"
	"time"
)

func Schedule(date string) time.Time {
	// https://gosamples.dev/date-time-format-cheatsheet/
	// the exercise doesn't use a predefined layout
	// why golang use this formar instead of DD-MM-YYYY?
	parsed, err := time.Parse("1/02/2006 15:04:05", date)
	if err != nil {
		panic(err)
	}
	return parsed
}

func HasPassed(date string) bool {
	parsed, err := time.Parse("January 2, 2006 15:04:05", date)
	if err != nil {
		panic(err)
	}
	return !parsed.After(time.Now())
}

func IsAfternoonAppointment(date string) bool {
	parsed, err := time.Parse("Monday, January 2, 2006 15:04:05", date)
	if err != nil {
		panic(err)
	}
	return parsed.Hour() >= 12 && parsed.Hour() <= 18
}

func Description(date string) string {
	parsed, err := time.Parse("1/2/2006 15:04:05", date)
	if err != nil {
		panic(err)
	}
	return parsed.Format("You have an appointment on Monday, January 2, 2006, at 15:04.")
}

func AnniversaryDate() time.Time {
	parsed, err := time.Parse("2006-01-02", fmt.Sprintf("%d-09-15", time.Now().Year()))
	if err != nil {
		panic(err)
	}
	return parsed
}
