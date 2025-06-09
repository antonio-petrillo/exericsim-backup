module Transpose exposing (transpose)


padder : Int -> List (Maybe a) -> List (Maybe a)
padder len list =
    let
        size = List.length list
        missing = len - size
    in
       List.append list (List.repeat missing Nothing)


combine : List (Maybe Char) -> List (List (Maybe Char)) -> List (List (Maybe Char))
combine element accumulator =
    List.map2 (::) element accumulator


constructRow : List (Maybe Char) -> String
constructRow list =
    case list of
        [] ->
            ""

        (x::[]) ->
            case x of
                Just char ->
                    String.fromChar char

                Nothing ->
                    ""

        (x::xs) ->
            let
                rest = constructRow xs
            in
                case x of
                    Just char ->
                        String.cons char rest

                    Nothing ->
                        if String.isEmpty rest then
                            ""
                        else
                            " " ++ rest


transpose : List String -> List String
transpose lines =
    let
        len = List.map String.length lines |> List.foldl max 0
        padderLen = padder len
        transposed =
            List.map (String.toList >> List.map Just) lines
                   |> List.map padderLen
                   |> List.foldr combine (List.repeat len [])
    in
        List.map constructRow transposed


{-

For anyone who reaches here, this exercise was pretty rough in elm y_y
My solution works as follows:
.1 Trasform each string in a List of Maybe Char (see later)
.2 Pad all the list (from prev step) to the same length (len = max length from lines), pad with `Nothing`
.3 Now we have a square matrix
.4 Perform the transpose:
   I've done this by creating an empty list and consing (append an element at a time) from the end of each row (hence List.foldr)
.5 Trasform each row back to a string
   This is done recursively:
   Base case 1: Empty -> Empty String
   Base case 2: Nothing -> Empty String
   Base case 2: Char -> String from Char

   Then Recurse:
   Opt 1. If the current char is not Nothing then cons that char
   Opt 2. If the current char is Nothing then append a space if the acculated string is not empty else return empty string
-}
