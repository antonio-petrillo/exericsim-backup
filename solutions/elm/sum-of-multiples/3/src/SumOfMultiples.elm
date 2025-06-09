module SumOfMultiples exposing (sumOfMultiples)


divisibleBy : Int -> Int -> Bool
divisibleBy num divisor =
    0 == modBy divisor num


sumOfMultiples : List Int -> Int -> Int
sumOfMultiples divisors limit =
    List.range 2 (limit - 1)
        |> List.filter (\n -> List.any (divisibleBy n) divisors) 
        |> List.sum
