module [steps]

steps : U64 -> Result U64 _
steps = \number ->
    if number <= 0 then
        Err NotPositiveInteger
    else
        tailSteps 0 number |> Ok
    
tailSteps : U64, U64 -> U64
tailSteps = \acc, curr -> 
    if curr == 1 then
        acc
    else if Num.isEven curr then
        tailSteps (acc + 1) (curr // 2)
    else
        tailSteps (acc + 1) (curr * 3 + 1)