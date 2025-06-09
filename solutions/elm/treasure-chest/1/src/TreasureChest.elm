module TreasureChest exposing (..)


type TreasureChest a
    = TreasureChest String a


getTreasure : String -> TreasureChest a -> Maybe a
getTreasure passwordAttempt (TreasureChest secret a) =
    if passwordAttempt == secret then 
        Just a
    else 
        Nothing


multiplyTreasure : (a -> List a) -> TreasureChest a -> TreasureChest (List a)
multiplyTreasure multiplier (TreasureChest secret a) =
    (multiplier a)
    |> TreasureChest secret 
