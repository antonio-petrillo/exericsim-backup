module PigLatin exposing (translate)


type Rule
    = Rule1
    | Rule2 (String, String)
    | Rule3 (String, String)
    | Rule4 (String, String)
    | None


startsWithVowel : String -> Bool
startsWithVowel str =
    case String.left 1 str of
        "a" -> True
        "e" -> True
        "i" -> True
        "o" -> True
        "u" -> True
        "y" -> True
        _   -> False


startWithConsonant : String -> Bool
startWithConsonant = not << startsWithVowel


getConsonantCluster : String -> (String, String)
getConsonantCluster str =
    if startsWithVowel str || String.isEmpty str then
        ("", str)
    else
        let
            (cluster, rest) = getConsonantCluster (String.dropLeft 1 str)
            head = String.left 1 str
        in
            (head ++ cluster, rest)


dispatch : String -> Rule
dispatch input =
    let
      (cluster, rest) = getConsonantCluster input
      headCluster = String.left 1 cluster
      lastCluster = String.right 1 cluster
      headRest = String.left 1 rest
    in
      if startsWithVowel input && (String.startsWith "y" input |> not) || String.startsWith "xr" input || String.startsWith "yt" input  then
         Rule1
      else
          case (lastCluster, headRest) of
              ("q", "u") -> Rule3 (String.dropRight 1 cluster, (String.dropLeft 1 rest))
              ("y", _)   -> Rule4 (cluster, rest)
              ("", "y")  -> Rule4 ("y", String.dropLeft 1 rest)
              (_, "")    -> None
              (_, _)     -> Rule2 (cluster, rest)


toPigLatin : String -> String
toPigLatin input =
    case dispatch input of
        None                  ->
            input

        Rule1                 ->
            input ++ "ay"

        Rule2 (cluster, rest) ->
            rest ++ cluster ++ "ay"

        Rule3 (cluster, rest) ->
            rest ++ cluster ++ "quay"

        Rule4 (cluster, rest) ->
            rest ++ cluster ++ "ay"

translate : String -> String
translate input =
    String.words input
        |> List.map toPigLatin
        |> String.join " "
