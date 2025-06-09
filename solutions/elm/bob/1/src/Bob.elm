module Bob exposing (hey)


hey : String -> String
hey remark =
    let 
        chars = String.filter Char.isAlpha remark
        initial = String.isEmpty chars |> not
        allCapital = String.foldl (\char acc -> acc && Char.isUpper char) initial chars
        isQuestion = "?" == (String.right 1 <| String.trimRight remark)
        isSilence  = String.isEmpty <| String.trim remark
    in
        case (allCapital, isQuestion, isSilence) of
            (False, True, _) -> "Sure."
            (True, False, False) -> "Whoa, chill out!"
            (True, True, _) -> "Calm down, I know what I'm doing!"
            (_, _, True) -> "Fine. Be that way!"
            _ -> "Whatever."
