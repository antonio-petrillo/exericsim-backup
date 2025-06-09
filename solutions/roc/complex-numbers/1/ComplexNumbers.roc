module [real, imaginary, add, sub, mul, div, conjugate, abs, exp]

Complex : { re : F64, im : F64 }

real : Complex -> F64
real = \z -> z.re

imaginary : Complex -> F64
imaginary = \z -> z.im

add : Complex, Complex -> Complex
add = \z1, z2 -> { re: z1.re + z2.re, im: z1.im + z2.im }

sub : Complex, Complex -> Complex
sub = \z1, z2 -> { re: z1.re - z2.re, im: z1.im - z2.im }

mul : Complex, Complex -> Complex
mul = \{re: a, im: b}, {re: c, im: d} -> {re: a * c - b * d, im: a * d + b * c} 

div : Complex, Complex -> Complex
div = \{re: a, im: b}, {re: c, im: d} ->
    den = c^2 + d^2
    {re: (a * c + b * d) / den, im: (b * c - a * d) / den}

conjugate : Complex -> Complex
conjugate = \z -> {re: z.re, im: -z.im}

abs : Complex -> F64
abs = \z -> z.re * z.re + z.im * z.im |> Num.sqrt

exp : Complex -> Complex
exp = \z -> {re: Num.e ^ z.re * (Num.cos z.im), im: Num.e ^ z.re * (Num.sin z.im)}
