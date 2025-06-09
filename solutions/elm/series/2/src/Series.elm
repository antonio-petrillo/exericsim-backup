module Series exposing (slices)


split : Int -> List String -> List (List String)
split n chars =
    if List.length chars < n then
        []
    else
        List.take n chars  :: split n (List.drop 1 chars)


parseInt : String -> Int
parseInt intStr = case String.toInt intStr of
                   Nothing -> -1 -- unreachable
                   Just n -> n

parseInts : List String -> List Int
parseInts xs =
   List.map parseInt xs


slices : Int -> String -> Result String (List (List Int))
slices size input =
    if String.isEmpty input then
        Err "series cannot be empty"
    else if size > String.length input then
        Err "slice length cannot be greater than series length"
    else if size == 0 then
        Err "slice length cannot be zero"
    else if size < 0 then
        Err "slice length cannot be negative"
    else
        String.split "" input
            |> split size
            |> List.map parseInts
            |> Ok
