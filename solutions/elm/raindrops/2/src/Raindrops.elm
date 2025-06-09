module Raindrops exposing (raindrops)

type alias Sound 
    = (Int, String)


sounds : List Sound
sounds = 
    [(3, "Pling"), (5, "Plang"), (7, "Plong")]


hasSound : Int -> Sound -> Maybe String
hasSound input (n, sound) =
    if modBy n input == 0 then 
        Just sound
    else 
        Nothing


raindrops : Int -> String
raindrops number =
    let 
        haveSounds = List.filterMap (hasSound number) sounds
    in
        case haveSounds of
            [] -> String.fromInt number
            _ -> String.concat haveSounds 
