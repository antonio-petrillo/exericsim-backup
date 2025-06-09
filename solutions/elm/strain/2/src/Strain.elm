module Strain exposing (discard, keep)


keep : (a -> Bool) -> List a -> List a
keep predicate list =
    case list of 
        [] ->
            []
        (x::xs) ->
            let
                rest = keep predicate xs
            in     
                if predicate x then 
                    x::rest
                else 
                    rest

-- keep : (a -> Bool) -> List a -> List a
-- keep predicate  =
--    List.foldr ( \el acc -> if predicate el then el::acc else acc ) []


discard : (a -> Bool) -> List a -> List a
discard predicate = keep (predicate >> not)
