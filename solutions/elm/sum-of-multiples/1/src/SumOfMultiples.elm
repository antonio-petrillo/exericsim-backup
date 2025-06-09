module SumOfMultiples exposing (sumOfMultiples)

import Set

sumOfMultiples : List Int -> Int -> Int
sumOfMultiples divisors limit =
    List.range 2 (limit - 1)
        |> List.filter (\n -> List.any (\divisor -> modBy divisor n == 0) divisors) 
        |> List.foldr (+) 0
