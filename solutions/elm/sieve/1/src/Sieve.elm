module Sieve exposing (primes)

primes : Int -> List Int
primes limit = 
    if limit < 2 then
        []
    else 
        primesTail (List.range 2 limit) [] |> List.reverse

primesTail : List Int -> List Int -> List Int
primesTail candidates acc =
    case candidates of 
        [] -> acc
        (x :: xs) -> primesTail (List.filter (\n -> 0 /= remainderBy x n) xs) (x :: acc)
        