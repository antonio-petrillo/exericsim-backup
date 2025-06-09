module EliudsEggs exposing (eggCount)

import Bitwise

{-
-- This is Bad each time a new function gets created, furthermore since the function is not exported there is nothing to worry about.
eggCount : Int -> Int
eggCount n =
    let 
        eggRec num count =
            if num == 0 then
                count
            else
                eggRec (Bitwise.and num (num - 1)) (count + 1)
    in
        eggRec n 0
-}

-- since I can't use eggCount'...
-- I've preferred to place the accumulator first so I can use currying to write eggCount 
kernighanAlgo : Int -> Int -> Int
kernighanAlgo acc n =
    case n of 
        0 ->
            acc
        _ ->
            kernighanAlgo (acc + 1) (Bitwise.and n (n - 1))

eggCount : Int -> Int
eggCount = kernighanAlgo 0
