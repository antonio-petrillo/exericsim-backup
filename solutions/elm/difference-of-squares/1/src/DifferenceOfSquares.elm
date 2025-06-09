module DifferenceOfSquares exposing (difference, squareOfSum, sumOfSquares)

square : Int -> Int
square n =
    n ^ 2


squareOfSum : Int -> Int
squareOfSum n =
    let 
        sum = n * (n + 1) // 2
    in
        square sum
        

sumOfSquares : Int -> Int
sumOfSquares n =
    List.range 0 n
        |> List.map square
        |> List.sum


difference : Int -> Int
difference n =
   squareOfSum n - sumOfSquares n
