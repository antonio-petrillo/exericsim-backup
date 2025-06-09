module LargestSeriesProduct exposing (largestProduct)


largestProduct : Int -> String -> Maybe Int
largestProduct length series =
    if length <= 0 then
        Nothing
    else
        List.foldl compute Nothing (partition length series)

compute : String -> Maybe Int -> Maybe Int
compute num acc =
    let
        maybeInts = String.split "" num
                   |> List.map String.toInt

        computed = List.filter (\c -> c /= Nothing) maybeInts
                   |> List.map (\n -> case n of
                                         Nothing -> 0 -- unreachable
                                         Just x -> x)
                   |> List.product
     in
         if List.any (\n -> n == Nothing) maybeInts  then
             Nothing
         else
             case acc of
                 Nothing -> Just computed
                 Just val -> if val < computed then Just computed else acc


partition : Int -> String -> List String
partition length series =
    if String.length series < length then
        []
    else
        (String.left length series)::(partition length (String.dropLeft 1 series))
