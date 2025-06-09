module LuciansLusciousLasagna exposing (elapsedTimeInMinutes, expectedMinutesInOven, preparationTimeInMinutes)

expectedMinutesInOven : Int
expectedMinutesInOven = 40

preparationTimeInMinutes : Int -> Int
preparationTimeInMinutes layers = 2 * layers

elapsedTimeInMinutes : Int -> Int -> Int
elapsedTimeInMinutes layers elapsed = preparationTimeInMinutes layers |> (+) elapsed