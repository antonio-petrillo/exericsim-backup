module ReverseString exposing (reverse)


-- reverse : String -> String
-- reverse = String.reverse

reverse : String -> String
reverse = String.foldr String.cons ""
