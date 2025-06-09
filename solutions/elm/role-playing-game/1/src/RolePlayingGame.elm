module RolePlayingGame exposing (Player, castSpell, introduce, revive)

type alias Player =
    { name : Maybe String
    , level : Int
    , health : Int
    , mana : Maybe Int
    }


introduce : Player -> String
introduce { name } =
    case name of
        Just aName ->
            aName

        Nothing ->
            "Mighty Magician"



revive : Player -> Maybe Player
revive player =
    if player.health == 0 then
        Just { player | health = 100
             , mana = case player.mana of
                          Just _ -> Just 100
                          Nothing -> Nothing }
    else
        Nothing


castSpell : Int -> Player -> ( Player, Int )
castSpell manaCost player =
    case player.mana of
        Just mana ->
            if mana > manaCost then
                ( { player | mana = mana - manaCost |> Just }, 2 * manaCost )
            else
                ( player, 0 )
        Nothing ->
            let
                newHealth = player.health - manaCost
            in
                ( { player | health = if newHealth < 0 then 0 else newHealth}, 0 )
