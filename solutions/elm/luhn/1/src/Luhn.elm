module Luhn exposing (valid)


type Pos
    = Odd
    | Even


toggle : Pos -> Pos
toggle pos =
    case pos of 
        Odd -> Even
        Even -> Odd


digitOffsetAscii : Int
digitOffsetAscii = Char.toCode '0' 


accumulate : Char -> (Int, Pos) -> (Int, Pos)
accumulate char (acc, pos) =
    let 
        nextPos = toggle pos

        digit =
             Char.toCode char - digitOffsetAscii

        doubledDigit = if digit >= 5 then digit * 2 - 9 else digit * 2
    in
        case pos of 
            Odd ->  (acc + doubledDigit, nextPos)
            Even -> (acc + digit, nextPos)


isLuhn : String -> Bool
isLuhn input =
    if String.length input <= 1 then 
        False 
    else 
        String.foldr accumulate (0, Even) input
            |> Tuple.first
            |> modBy 10
            |> (==) 0


valid : String -> Bool
valid input =
    String.all (\c -> Char.isDigit c || c == ' ') input
        && isLuhn (String.filter Char.isDigit input)
