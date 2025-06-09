module Grains exposing (square)

import Bitwise

square : Int -> Maybe Int
square n =
    if n > 0 && n < 33 then
        -- Just (Bitwise.shiftLeftBy (n - 1) 1) -- why it doesn't work?
        2 ^ (n - 1)
            |> Just
    else
        Nothing
