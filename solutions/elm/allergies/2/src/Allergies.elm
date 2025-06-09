module Allergies exposing (Allergy(..), isAllergicTo, toList)

import Bitwise

type Allergy
    = Eggs
    | Peanuts
    | Shellfish
    | Strawberries
    | Tomatoes
    | Chocolate
    | Pollen
    | Cats


allergyToId : Allergy -> Int
allergyToId allergy =
    case allergy of 
        Eggs -> 1
        Peanuts -> 2
        Shellfish -> 4
        Strawberries -> 8
        Tomatoes -> 16
        Chocolate -> 32
        Pollen -> 64
        Cats -> 128

isAllergicTo : Allergy -> Int -> Bool
isAllergicTo allergy score =
    let 
        id = allergyToId allergy
    in
        0 /= (Bitwise.and id score)

isAllergicTo2 : Int -> Allergy -> Bool
isAllergicTo2 score allergy =
    let 
        id = allergyToId allergy
    in
        0 /= (Bitwise.and id score)
    
allergies : List Allergy
allergies = [Eggs, Peanuts, Shellfish, Strawberries, Tomatoes, Chocolate, Pollen, Cats] 

toList : Int -> List Allergy
toList score =
    List.filter (isAllergicTo2 score) allergies
