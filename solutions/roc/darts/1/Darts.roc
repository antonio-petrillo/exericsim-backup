module [score]

score : F64, F64 -> U64
score = |x, y|
    ds = x * x + y * y
    if ds <= 1 then 10
    else if ds <= 25 then 5
    else if ds <= 100 then 1
    else 0

