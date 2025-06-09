module Etl exposing (transform)

import Dict exposing (Dict)


transform : Dict Int (List String) -> Dict String Int
transform input =
    Dict.foldl
        (\key value acc ->
             List.foldl
                  (\str dict -> Dict.insert (String.toLower str)
                  key
                  dict)
             acc
             value)
        Dict.empty
        input
