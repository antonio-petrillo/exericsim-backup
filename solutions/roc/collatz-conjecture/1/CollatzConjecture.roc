module [steps]

steps : U64 -> Result U64 _
steps = \number ->
    if number <= 0 then
        Err NotPositiveInteger
    else
        tailSteps 0 number
    
tailSteps : U64, U64 -> Result U64 _
tailSteps = \acc, curr -> 
    if curr == 1 then
        Ok(acc)
    else if curr % 2 == 0 then
        tailSteps (acc + 1) (curr // 2)
    else
        tailSteps (acc + 1) (curr * 3 + 1)