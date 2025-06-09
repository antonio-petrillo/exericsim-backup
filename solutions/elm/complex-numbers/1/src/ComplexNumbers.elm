module ComplexNumbers exposing
    ( Complex
    , abs
    , add
    , conjugate
    , div
    , exp
    , fromPair
    , fromReal
    , imaginary
    , mul
    , real
    , sub
    )


type alias Complex
    = { real : Float, imaginary : Float }


fromPair : ( Float, Float ) -> Complex
fromPair (r, i) =
    { real = r 
    , imaginary = i }
    


fromReal : Float -> Complex
fromReal float =
    { real = float
    , imaginary = 0.0}


real : Complex -> Float
real = .real 


imaginary : Complex -> Float
imaginary = .imaginary 


conjugate : Complex -> Complex
conjugate z =
    { z | imaginary = -z.imaginary }


abs : Complex -> Float
abs z = 
    let 
        a = z.real
        b = z.imaginary
    in
        sqrt(a * a + b * b)



add : Complex -> Complex -> Complex
add z1 z2 =
    { real = z1.real + z2.real
    , imaginary = z1.imaginary + z2.imaginary }


sub : Complex -> Complex -> Complex
sub z1 z2 =
    { real = z1.real - z2.real
    , imaginary = z1.imaginary - z2.imaginary }


mul : Complex -> Complex -> Complex
mul z1 z2 =
    let 
        a = z1.real
        b = z1.imaginary
        c = z2.real
        d = z2.imaginary
    in
        { real = a * c - b * d
        , imaginary = a * d + b * c }


div : Complex -> Complex -> Complex
div z1 z2 =
    let 
        a = z1.real
        b = z1.imaginary
        c = z2.real
        d = z2.imaginary

        den = c * c + d * d
    in
        { real = (a * c + b * d) / den
        , imaginary = (b * c - a * d) / den}


exp : Complex -> Complex
exp z =
    let 
        a = z.real
        b = z.imaginary
        modulus = e ^ a
    in 
        { real = modulus * (cos b)
        , imaginary = modulus * (sin b) }
