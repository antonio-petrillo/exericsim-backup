package allergies

import (
    "strings"
)

var allergens = []string{"eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"}

func Allergies(allergies uint) (res []string) {
	for _, allergen := range allergens {
        if AllergicTo(allergies, allergen) {
         	res = append(res, allergen)
        }
    }
	return
}

func allergenToScore(allergen string) uint {
    switch strings.ToLower(allergen) {
        case "eggs":
        	return 1
        case "peanuts":
        	return 2
        case "shellfish":
        	return 4
        case "strawberries":
        	return 8
        case "tomatoes":
        	return 16
        case "chocolate":
        	return 32
        case "pollen":
        	return 64
        case "cats":
        	return 128
        default:
        	return 0
    }
}

func AllergicTo(allergies uint, allergen string) bool {
	return allergenToScore(allergen) & allergies != 0
}
