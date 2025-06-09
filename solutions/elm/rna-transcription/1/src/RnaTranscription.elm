module RnaTranscription exposing (toRNA)


mapping : Char -> Maybe Char
mapping c =
    case c of
        'A' -> Just 'U'
        'T' -> Just 'A'
        'C' -> Just 'G'
        'G' -> Just 'C'
        _   -> Nothing


update : Char -> Result String String -> Result String String
update char res =
    case res of
        Err msg -> Err msg
        Ok  acc -> let
                       mapped = mapping char
                   in
                       case mapped of
                           Nothing -> Err "Invalid Input"
                           Just c  -> Ok (String.cons c acc)


toRNA : String -> Result String String
toRNA dna =
    String.toUpper dna
        |> String.foldr update (Ok "")
