package allergies

import "base:intrinsics"

Allergen :: enum {
	Eggs,
	Peanuts,
	Shellfish,
	Strawberries,
	Tomatoes,
	Chocolate,
	Pollen,
	Cats,
}

allergic_to :: proc(score: int, allergen: Allergen) -> bool {
    mask := 1 << uint(allergen)
    return score & mask != 0
}

list :: proc(score: int) -> []Allergen {
    mask :: 1 << (uint(Allergen.Cats) + 1) - 1
    size := intrinsics.count_ones(score & mask)
    allergens := make([]Allergen, size)

    i := 0
    for allergen in Allergen {
        if allergic_to(score, allergen) {
            allergens[i] = allergen
            i += 1
        }
    }

    return allergens
}
