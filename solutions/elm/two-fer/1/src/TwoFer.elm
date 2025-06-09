module TwoFer exposing (twoFer)


twoFer : Maybe String -> String
twoFer name =
    let
        _name = case name of
                    Nothing -> "you"
                    Just n -> n
    in
        "One for " ++ _name ++ ", one for me."
