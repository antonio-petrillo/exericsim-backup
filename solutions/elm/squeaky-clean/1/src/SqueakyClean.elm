module SqueakyClean exposing (clean, clean1, clean2, clean3, clean4)


clean1 : String -> String
clean1 = String.replace " " "_"


replaceHelper : Char -> String -> String
replaceHelper s acc =
    let
        char = case s of
                   '\n' -> "[CTRL]"
                   '\r' -> "[CTRL]"
                   '\t' -> "[CTRL]"
                   _ -> String.fromChar s
    in
        acc ++ char

clean2 : String -> String
clean2 =
    clean1 >> String.foldl replaceHelper ""


capitalize : Int -> String -> String
capitalize index str =
    if index > 0 then
        case String.uncons str of
            Nothing ->
                ""
            Just (car, cdr) ->
                String.cons (Char.toUpper car) cdr
    else
        str

clean3 : String -> String
clean3 str =
    String.split "-" (clean2 str)
        |> List.indexedMap capitalize
        |> String.concat


clean4 : String -> String
clean4 = clean3 >> String.filter (\c -> c < '0' || c > '9')


clean : String -> String
clean = clean4 >> String.filter (\c -> c < 'α' || c > 'ω')
