module SecureTreasureChest exposing (..)


type Password
    = Password String


type SecureTreasureChest treasure
    = SecureTreasureChest String treasure


createPassword : String -> Maybe Password
createPassword passwordCandidate =
    if String.length passwordCandidate < 8 then
        Nothing
    else
        Just (Password passwordCandidate)


createTreasure :  a -> Password -> SecureTreasureChest a 
createTreasure treasure (Password secret) =
    SecureTreasureChest secret treasure
    

getTreasure : String -> SecureTreasureChest a -> Maybe a
getTreasure attempt (SecureTreasureChest secret treasure) =
    if attempt == secret then 
        Just treasure
    else
        Nothing
    
