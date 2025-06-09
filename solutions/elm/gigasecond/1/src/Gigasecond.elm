module Gigasecond exposing (add)

import Time


gigasecond : Int
gigasecond = 1000000000000


add : Time.Posix -> Time.Posix
add timestamp =
    Time.posixToMillis timestamp
        |> (<|) (\sec -> sec + gigasecond)
        |> Time.millisToPosix
