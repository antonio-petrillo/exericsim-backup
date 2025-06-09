module RunLengthEncoding exposing (decode, encode)

type alias Counting
    = { letter : Char
      , count  : Int
      }

encodeCounting : Counting -> String
encodeCounting c =
    let
        cStr = String.fromChar c.letter
    in
        if c.count == 1 then
            cStr
        else
            (String.fromInt c.count) ++ cStr

takeWhile : (a -> Bool) -> List a -> List a
takeWhile pred l =
    case l of
        [] -> []
        (x::xs) ->
            if pred x then
                x::(takeWhile pred xs)
            else
                []

splitWhileEqual : List Char -> List Counting
splitWhileEqual chars =
    case chars of
        [] -> []
        (x::_) ->
            let
                eqs = takeWhile (\c -> c == x) chars
                size = List.length eqs
            in
                if List.isEmpty eqs then
                    []
                else
                    { letter = x, count = size } :: (splitWhileEqual (List.drop size chars))

encode : String -> String
encode string =
    String.toList string
        |> splitWhileEqual
        |> List.map encodeCounting
        |> String.concat


splitWithCounting : List Char -> List Counting
splitWithCounting chars =
    case chars of
       [] -> []
       l ->
           let
               nums = takeWhile (\c -> c >= '0' && c <= '9') l
               size = (List.length nums)
               rems = List.drop size l
               char = case List.head rems of
                          Nothing -> '\r' -- unreachable
                          Just c -> c
               count = case String.fromList nums |> String.toInt of
                          Nothing -> 1 -- a single character
                          Just n -> n
           in
               { letter = char, count = count } :: (List.drop 1 rems |> splitWithCounting)


decode : String -> String
decode string =
    String.toList string
        |> splitWithCounting
        |> List.map (\c -> String.repeat c.count (String.fromChar c.letter))
        |> String.concat
