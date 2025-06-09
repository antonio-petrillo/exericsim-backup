module TwoFer exposing (twoFer)


twoFer : Maybe String -> String
twoFer name =
    let
        aName = case name of
                    Nothing -> "you"
                    Just n -> n
    in
        "One for " ++ aName ++ ", one for me."
