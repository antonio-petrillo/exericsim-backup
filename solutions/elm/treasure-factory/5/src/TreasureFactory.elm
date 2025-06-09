module TreasureFactory exposing (TreasureChest, getTreasure, makeChest, makeTreasureChest, secureChest, uniqueTreasures)


type TreasureChest treasure
    = TreasureChest String treasure


getTreasure : String -> TreasureChest treasure -> Maybe treasure
getTreasure passwordAttempt (TreasureChest password treasure) =
    if passwordAttempt == password then
        Just treasure

    else
        Nothing


type Chest treasure rules
    = Chest String treasure


makeChest : String -> treasure -> Chest treasure {}
makeChest password treasure =
    Chest password treasure


secureChest : Chest treasure conditions -> Maybe (Chest treasure { conditions | securePassword: ()})
secureChest (Chest passwd treasure) =
    if String.length passwd < 8 then
        Nothing
    else
        Just (Chest passwd treasure)


toUnique : Chest treasure conditions -> Chest treasure { conditions | uniqueTreasure: () }
toUnique (Chest passwd treasure) =
    Chest passwd treasure


isTreasureEqual : Chest treasure conditions -> Chest treasure conditions -> Bool
isTreasureEqual (Chest _ treasure1) (Chest _ treasure2) =
    treasure1 == treasure2


keepIfUnique : List (Chest a conditions) -> (Chest a conditions) -> List (Chest a { conditions | uniqueTreasure: () }) -> List (Chest a { conditions | uniqueTreasure: () })
keepIfUnique list chest acc =
    if (List.length <| List.filter (isTreasureEqual chest) list) > 1 then
        acc
    else
        toUnique chest :: acc


-- I don't like this O(n^2), maybe try with a Dict
uniqueTreasures : List (Chest treasure conditions) -> List (Chest treasure { conditions | uniqueTreasure: () })
uniqueTreasures chests = List.foldr (keepIfUnique chests) [] chests


makeTreasureChest : Chest treasure { conditions | securePassword : (), uniqueTreasure : () } -> TreasureChest treasure
makeTreasureChest (Chest passwd treasure) =
   TreasureChest passwd treasure
