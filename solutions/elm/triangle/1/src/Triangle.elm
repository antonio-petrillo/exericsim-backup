module Triangle exposing (Triangle(..), triangleKind)


type Triangle
    = Equilateral
    | Isosceles
    | Scalene


triangleKind : number -> number -> number -> Result String Triangle
triangleKind x y z =
    if x <= 0 || y <= 0 || z <= 0 then
        Err "Invalid lengths"
    else if x + y < z || x + z < y || y + z < x then
        Err "Violates inequality"
    else
        Ok
            <| if x == y && y == z then
                   Equilateral
               else if x == y || x == z || y == z  then
                   Isosceles
               else
                   Scalene
