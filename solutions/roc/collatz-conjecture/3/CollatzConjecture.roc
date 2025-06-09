module [steps]

steps : U64 -> Result U64 _
steps = \number ->
    loop = \acc, curr ->
        if curr == 1 then
            acc
        else if Num.isEven curr then
            loop (acc + 1) (curr // 2)
        else 
            loop (acc + 1) (curr * 3 + 1)
            
    if number <= 0 then
        Err NotPositiveInteger
    else
        loop 0 number |> Ok