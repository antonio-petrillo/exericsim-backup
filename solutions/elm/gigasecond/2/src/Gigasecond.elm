module Gigasecond exposing (add)

import Time


gigaMilliSecond : Int
gigaMilliSecond = 10 ^ 9 * 1000 -- gigasecond * 1000 -> gigamillisecond


add : Time.Posix -> Time.Posix
add =
    Time.posixToMillis 
        >> ((+) gigaMilliSecond)
        >> Time.millisToPosix
