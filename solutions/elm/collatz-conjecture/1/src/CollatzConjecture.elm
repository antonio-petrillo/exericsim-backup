module CollatzConjecture exposing (collatz)

import Bitwise

collatz : Int -> Result String Int
collatz start =
    if start <= 0 then
        Err "Only positive integers are allowed"
    else
        Ok (collatzRec start 0)

collatzRec : Int -> Int -> Int
collatzRec n count =
    if n == 1 then
        count
    else if Bitwise.and n 1 == 0 then -- even
        collatzRec (Bitwise.shiftRightBy 1 n) (count + 1)
    else
        collatzRec ( 3 * n + 1) (count + 1)
