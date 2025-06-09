module BlorkemonCards exposing
    ( Card
    , compareShinyPower
    , expectedWinner
    , isMorePowerful
    , maxPower
    , sortByCoolness
    , sortByMonsterName
    )


type alias Card =
    { monster : String, power : Int, shiny : Bool }


isMorePowerful : Card -> Card -> Bool
isMorePowerful card1 card2 =
    case compare card1.power card2.power of
        GT -> True
        _  -> False


maxPower : Card -> Card -> Int
maxPower card1 card2 =
    max card1.power card2.power


compareName : Card -> Card -> Order
compareName c1 c2 = compare c1.monster c2.monster


sortByMonsterName : List Card -> List Card
sortByMonsterName cards =
    List.sortWith compareName cards


compareByCoolness : Card -> Card -> Order
compareByCoolness c1 c2 = 
    case compareShiny c1 c2 of 
        LT -> GT
        GT -> LT
        EQ -> compare c2.power c1.power


sortByCoolness : List Card -> List Card
sortByCoolness = List.sortWith compareByCoolness


compareShiny : Card -> Card -> Order 
compareShiny c1 c2 =
    case (c1.shiny, c2.shiny) of
       (True, True) -> EQ
       (False, True) -> LT
       (True, False) -> GT
       (False, False) -> EQ

compareShinyPower : Card -> Card -> Order
compareShinyPower c1 c2 =
   case compare c1.power c2.power of 
       LT -> LT
       GT -> GT
       EQ -> compareShiny c1 c2


expectedWinner : Card -> Card -> String
expectedWinner card1 card2 =
    case compareShinyPower card1 card2 of
        LT -> card2.monster
        GT -> card1.monster
        EQ -> "too close to call"
