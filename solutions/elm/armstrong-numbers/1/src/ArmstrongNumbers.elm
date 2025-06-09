module ArmstrongNumbers exposing (isArmstrongNumber)


digits : Int -> (List Int, Int)
digits n =
    if n == 0 then
        ([], 0)
    else
       let
           (xs, size) = n // 10 |> digits
       in
           ((modBy 10 n)::xs, size + 1)

isArmstrongNumber : Int -> Bool
isArmstrongNumber nb =
       let
           (ds, size) = digits nb
       in
           (List.foldl (\n acc -> acc + n ^ size) 0 ds) == nb
