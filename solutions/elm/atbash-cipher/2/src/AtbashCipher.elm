module AtbashCipher exposing (decode, encode)


shift : Char -> Char
shift char =
    case char of 
        'a' -> 'z'
        'b' -> 'y'
        'c' -> 'x'
        'd' -> 'w'
        'e' -> 'v'
        'f' -> 'u'
        'g' -> 't'
        'h' -> 's'
        'i' -> 'r'
        'j' -> 'q'
        'k' -> 'p'
        'l' -> 'o'
        'm' -> 'n'
        'n' -> 'm'
        'o' -> 'l'
        'p' -> 'k'
        'q' -> 'j'
        'r' -> 'i'
        's' -> 'h'
        't' -> 'g'
        'u' -> 'f'
        'v' -> 'e'
        'w' -> 'd'
        'x' -> 'c'
        'y' -> 'b'
        'z' -> 'a'
        _ -> char


splitIn5 : String -> String
splitIn5 str =
    if String.isEmpty str then
        str 
    else 
        let 
            rest = splitIn5 <| String.dropLeft 5 str
            five = String.left 5 str
        in 
            if String.isEmpty rest then 
                five
            else 
                five ++ " " ++ rest

encode : String -> String
encode = 
    String.filter Char.isAlphaNum 
        >> String.toLower
        >> String.map shift
        >> splitIn5


decode : String -> String
decode = encode >> String.filter (\c -> c /= ' ')
