module MagicianInTraining exposing (..)

-- todo: import the Array module
import Array exposing (..)
import Bitwise exposing (and)

getCard : Int -> Array Int -> Maybe Int
getCard = get

setCard : Int -> Int -> Array Int -> Array Int
setCard = set


addCard : Int -> Array Int -> Array Int
addCard = push


removeCard : Int -> Array Int -> Array Int
removeCard index deck =
    let
        prev = slice 0 index deck
        follow = slice (index + 1) (length deck) deck
    in
        append prev follow


evenCardCount : Array Int -> Int
evenCardCount deck =
    filter (\n -> and n 1 == 0) deck
        |> length
