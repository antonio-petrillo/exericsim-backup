module NucleotideCount exposing (nucleotideCounts)


type alias NucleotideCounts =
    { a : Int
    , t : Int
    , c : Int
    , g : Int
    }

isValidNucleotide : String -> Bool
isValidNucleotide =
    String.toLower
        >>  String.foldl (\c acc -> acc && (c == 'a' || c == 't' || c == 'c' || c == 'g' )) True

updateCount : Char -> NucleotideCounts -> NucleotideCounts
updateCount c count =
    case c of
        'a' -> { count | a = count.a + 1 }
        't' -> { count | t = count.t + 1 }
        'c' -> { count | c = count.c + 1 }
        'g' -> { count | g = count.g + 1 }
        _ -> count -- unreachable


nucleotideCounts : String -> Result String NucleotideCounts
nucleotideCounts sequence =
    if isValidNucleotide sequence then
        String.toLower sequence
            |> String.foldl updateCount { a = 0, t = 0, c = 0, g = 0 }
            |> Ok
    else
        (Err "Invalid nucleotide in strand")
