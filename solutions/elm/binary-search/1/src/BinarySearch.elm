module BinarySearch exposing (find)

import Array exposing (Array)


find : Int -> Array Int -> Maybe Int
find = findRec 0

findRec : Int -> Int -> Array Int -> Maybe Int
findRec start target xs =
    let
        size = Array.length xs
        midpoint = size // 2
    in
        case Array.get midpoint xs of
            Nothing ->
                Nothing

            Just value ->
                case compare target value of
                    EQ ->
                        start + midpoint |> Just

                    GT ->
                       findRec (start + midpoint + 1) target (Array.slice (midpoint + 1) size xs)

                    LT ->
                       findRec start target (Array.slice 0 midpoint xs)
