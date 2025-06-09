module TicketPlease exposing (..)

import TicketPleaseSupport exposing (Status(..), Ticket(..), User(..))


emptyComment : ( User, String ) -> Bool
emptyComment (_, comment) =
    String.isEmpty comment


numberOfCreatorComments : Ticket -> Int
numberOfCreatorComments (Ticket { createdBy, comments })  =
    let 
        (name, _) = createdBy
    in     
        List.foldl (\(n, _) acc -> if n == name then acc + 1 else acc) 0 comments    


assignedToDevTeam : Ticket -> Bool
assignedToDevTeam (Ticket { assignedTo }) =
    case assignedTo of
        Nothing -> False
        Just user -> case user of 
            User "Bob" -> True
            User "Alice" -> True
            User "Charlie" -> True
            _ -> False
    


assignTicketTo : User -> Ticket -> Ticket
assignTicketTo user ((Ticket t) as ticket) =
    case t.status of 
        New -> Ticket { t | assignedTo = Just user, status = InProgress }
        Archived -> ticket
        _ -> Ticket { t | assignedTo = Just user}
    
