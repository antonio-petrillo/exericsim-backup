module Isogram exposing (isIsogram)


import Dict


accumulate : Maybe Int -> Maybe Int
accumulate count = 
    case count of
        Nothing -> Just 1
        Just n -> Just (n + 1)

isIsogram : String -> Bool
isIsogram =
    String.toLower
        >> String.foldl 
            (\char dict ->
                if Char.isLower char then 
                    Dict.update char accumulate dict
                else 
                    dict
            )
            Dict.empty
        >> Dict.values
        >> List.all (\count -> count == 1)
         
