module WordCount exposing (wordCount)

import Dict exposing (Dict)


validChar : Char -> Bool
validChar c = c >= 'a' && c <= 'z'
              || c >= 'A' && c <= 'Z'
              || c >= '0' && c <= '9'
              || c == '\'' || c == ' ' -- then I need to unquote


updateCount : Maybe Int -> Maybe Int
updateCount value =
    case value of
        Nothing -> Just 1
        Just v -> Just (v + 1)


updateDict : String -> Dict String Int -> Dict String Int
updateDict key dict = Dict.update key updateCount dict


-- I don't need this, but I think it's a good exercise (although Elm should provide by default takeWhile and dropWhile)
dropWhile : (String -> Bool) -> String -> String
dropWhile p s =
    if String.left 1 s |> p then
        String.dropLeft 1 s |> dropWhile p
    else
        s


isQuote : String -> Bool
isQuote s = s == "'"


unquote : String -> String
unquote s = String.reverse s
            |> dropWhile isQuote
            |> String.reverse
            |> dropWhile isQuote


wordCount : String -> Dict String Int
wordCount sentence =
    String.replace "," " " sentence
        |> String.filter validChar
        |> String.words
        |> List.map (unquote >> String.toLower)
        |> List.foldl updateDict Dict.empty
