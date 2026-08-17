import std/bitops
  
type
  Allergen* = enum
    Eggs, Peanuts, Shellfish, Strawberries, Tomatoes, Chocolate, Pollen, Cats

proc allergies*(score: int): set[Allergen] =
  cast[set[Allergen]](score)

proc isAllergicTo*(score: int, allergen: Allergen): bool =
  result = allergen in allergies(score)


