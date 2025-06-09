module TisburyTreasureHunt exposing (..)

-- Consider defining a type alias for TreasureLocation,
-- Treasure, PlaceLocation and Place,
-- and using them in the function type annotations

type alias PlaceLocation
    = ( Char, Int )

type alias TreasureLocation
    = ( Int, Char )


placeLocationToTreasureLocation : ( Char, Int ) -> ( Int, Char )
placeLocationToTreasureLocation (place, location) = (location, place)


treasureLocationMatchesPlaceLocation : ( Char, Int ) -> ( Int, Char ) -> Bool
treasureLocationMatchesPlaceLocation placeLocation treasureLocation =
    treasureLocation ==  placeLocationToTreasureLocation placeLocation


countPlaceTreasures : ( String, ( Char, Int ) ) -> List ( String, ( Int, Char ) ) -> Int
countPlaceTreasures (_, place) treasures =
    let
        predicate = treasureLocationMatchesPlaceLocation place
    in
        List.filter (\(_, tresLoc) -> predicate tresLoc) treasures
            |> List.length



specialCaseSwapPossible : ( String, TreasureLocation ) -> ( String, PlaceLocation ) -> ( String, TreasureLocation ) -> Bool
specialCaseSwapPossible ( foundTreasure, _ ) ( place, _ ) ( desiredTreasure, _ ) =
   case (foundTreasure, place, desiredTreasure) of
       ("Brass Spyglass", "Abandoned Lighthouse", _) -> True
       ("Amethyst Octopus", "Stormy Breakwater", "Crystal Crab") -> True
       ("Amethyst Octopus", "Stormy Breakwater", "Glass Starfish") -> True
       ("Vintage Pirate Hat", "Harbor Managers Office", "Model Ship in Large Bottle") -> True
       ("Vintage Pirate Hat", "Harbor Managers Office", "Antique Glass Fishnet Float") -> True
       _ -> False
