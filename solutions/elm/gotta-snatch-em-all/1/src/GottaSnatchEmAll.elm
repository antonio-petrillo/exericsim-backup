module GottaSnatchEmAll exposing (..)

import Set exposing (Set)


type alias Card =
    String


newCollection : Card -> Set Card
newCollection = Set.singleton


addCard : Card -> Set Card -> ( Bool, Set Card )
addCard card collection = ( Set.member card collection, Set.insert card collection )


tradeCard : Card -> Card -> Set Card -> ( Bool, Set Card )
tradeCard yourCard theirCard collection =
   let
       worthTrade = Set.member yourCard collection  && (Set.member theirCard collection |> not)
   in
       ( worthTrade, Set.remove yourCard collection |> Set.insert theirCard )


removeDuplicates : List Card -> List Card
removeDuplicates cards =
    Set.fromList cards
        |> Set.toList
        |> List.sort

extraCards : Set Card -> Set Card -> Int
extraCards yourCollection theirCollection =
   Set.diff yourCollection theirCollection |> Set.size


boringCards : List (Set Card) -> List Card
boringCards collections =
    case collections of
        (x::xs) ->
            List.foldl Set.intersect x collections
                    |> Set.toList |> List.sort

        [] ->
            []

totalCards : List (Set Card) -> Int
totalCards collections =
   case collections of
       (x :: xs) ->
           List.foldl Set.union x collections
               |> Set.size
       [] ->
           0


splitShinyCards : Set Card -> ( List Card, List Card )
splitShinyCards collection =
    Set.partition (String.startsWith "Shiny") collection
        |> Tuple.mapBoth Set.toList Set.toList
