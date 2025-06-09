package lasagna

func PreparationTime(layers []string, minutePerLayers int) int {
	if minutePerLayers == 0 {
		minutePerLayers = 2
	}
	return minutePerLayers * len(layers)
}

func Quantities(layers []string) (grams int, liters float64) {
	for _, v := range layers {
		if v == "sauce" {
			liters += 0.2
		}
		if v == "noodles" {
			grams += 50
		}
	}
	return grams, liters
}

func AddSecretIngredient(friendRecipe, myRecipe []string) {
	myRecipe[len(myRecipe)-1] = friendRecipe[len(friendRecipe)-1]
}

func ScaleRecipe(quantities []float64, portions int) (scaled []float64) {
	scale := float64(portions) / 2.0
	for _, v := range quantities {
		scaled = append(scaled, v*scale)
	}
	return scaled
}
