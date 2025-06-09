module SumOfMultiples exposing (sumOfMultiples)


sumOfMultiples : List Int -> Int -> Int
sumOfMultiples divisors limit =
    List.range 2 (limit - 1)
        |> List.filter (\n -> List.any (\divisor -> modBy divisor n == 0) divisors) 
        |> List.sum
