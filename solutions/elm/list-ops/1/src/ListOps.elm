module ListOps exposing
    ( append
    , concat
    , filter
    , foldl
    , foldr
    , length
    , map
    , reverse
    )


length : List a -> Int
length = foldl (\_ len -> len +1) 0


reverse : List a -> List a
reverse =
    foldl (::) []


foldl : (a -> b -> b) -> b -> List a -> b
foldl f acc list =
    case list of 
        [] -> acc
        (x::xs) -> foldl f (f x acc) xs


foldr : (a -> b -> b) -> b -> List a -> b
foldr f acc list =
    case list of
        [] -> acc
        (x::xs) -> f x (foldr f acc xs) 


map : (a -> b) -> List a -> List b
map f = foldr (\el els -> f el::els)  [ ]
-- OR
-- map f list =
--     case list of 
--         [] -> []
--         (x::xs) -> f x :: map f xs 
    


filter : (a -> Bool) -> List a -> List a
filter f = foldr (\el els -> if f el then el::els else els) []
-- OR
-- filter f l =
--     foldl (\el els -> if f el then el::els else els) [] l 
--         |> reverse    
-- OR
-- filter f = foldl (\el els -> if f el then els ++ [el] else els) []
-- OR
-- filter f list =
--     case list of 
--         [] -> []
--         (x::xs) -> if f x then
--                         x::filter f xs
--                     else 
--                         filter f xs


append : List a -> List a -> List a
append xs ys = foldr (::) ys xs 


concat : List (List a) -> List a
concat = foldr append []
