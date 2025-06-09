module [steps]

steps : U64 -> Result U64 _
steps = \number ->
    loop = \acc, curr ->
        when curr is
            1 -> acc
            n if Num.isEven n -> loop (acc + 1) (curr // 2)
            _ -> loop (acc + 1) (curr * 3 + 1)
            
    if number <= 0 then
        Err NotPositiveInteger
    else
        loop 0 number |> Ok