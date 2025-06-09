module [eggCount]

eggCount : U64 -> U64
eggCount = \number ->
    loop = \count, n ->
        when n is 
            0 -> count
            _ -> loop (count + 1) (Num.bitwiseAnd n (n - 1))
    loop 0 number
