module Pangram exposing (isPangram)

import Bitwise


allPresent : Int
allPresent = 0x03FFFFFF


charOffset : Int
charOffset = Char.toCode 'a'
-- charOffset = 97 -- 'a' in ascii


accumulator : Char -> Int -> Int
accumulator char acc =
    case Char.isAlpha char of
        True ->
            let
                offset = Char.toCode char - charOffset
                bitToSet = Bitwise.shiftLeftBy offset 1
            in
                Bitwise.or acc bitToSet
        False ->
            acc

isPangram : String -> Bool
isPangram sentence =
    String.foldl
        accumulator
        0
        (String.toLower sentence)
        |> (==) allPresent
