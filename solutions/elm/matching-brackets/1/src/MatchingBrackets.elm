module MatchingBrackets exposing (isPaired)

checkBalance : Char -> (String, Bool) -> (String, Bool)
checkBalance char (stack, balanced) =
   case char of
       '(' -> ( String.cons char stack, balanced )
       '[' -> ( String.cons char stack, balanced )
       '{' -> ( String.cons char stack, balanced )
       ')' -> ( String.dropLeft 1 stack, balanced && ((String.left 1 stack) == "("))
       ']' -> ( String.dropLeft 1 stack, balanced && ((String.left 1 stack) == "["))
       '}' -> ( String.dropLeft 1 stack, balanced && ((String.left 1 stack) == "{"))
       _   -> ( stack, balanced )

isPaired : String -> Bool
isPaired input =
    String.foldl checkBalance ("", True) input
            |> \(stack, balanced) -> stack == "" && balanced
