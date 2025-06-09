module MariosMarvellousLasagna exposing (remainingTimeInMinutes)

expectedMinutesInOven : Int
expectedMinutesInOven = 40

preparationTimeInMinutes : Int -> Int
preparationTimeInMinutes layers =
    layers * 2

remainingTimeInMinutes : Int -> Int -> Int
remainingTimeInMinutes layers elapsed =
    let
        prep = preparationTimeInMinutes layers
    in
        expectedMinutesInOven + prep - elapsed
