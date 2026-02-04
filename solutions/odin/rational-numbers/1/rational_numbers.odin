package rational_numbers

import "core:math"
import "base:builtin"

// Implement the Rational struct as needed
Rational :: struct {
    num: int,
    den: int,
}

simplify_fraction :: proc(a: Rational) -> Rational {
    gcd := math.gcd(a.num, a.den) * (a.den < 0 ? -1 : 1)
    return Rational{a.num / gcd, a.den / gcd}
}

simplify_fraction_from_num :: proc(num, den: int) -> Rational {
    gcd := math.gcd(num, den) * (den < 0 ? -1 : 1)
    return Rational{num / gcd, den / gcd}
}

reduce :: proc{ simplify_fraction, simplify_fraction_from_num }

abs :: proc(a: Rational) -> Rational {
    a := reduce(a)
    return Rational{
        num = builtin.abs(a.num),
        den = a.den,
    }
}

add :: proc(a: Rational, b: Rational) -> Rational {
    den := a.den * b.den
    num := a.num * b.den + b.num * a.den
    return reduce(num, den)
}

div :: proc(a: Rational, b: Rational) -> Rational {
    b := b
    b.num, b.den = b.den, b.num
    return mul(a, b)
}

exprational :: proc(a: Rational, power: int) -> Rational {
    if power < 0 {
        r := reduce(Rational{a.den, a.num})
        return exprational(r, -power)
    }
    r := Rational{
        num = int_pow(a.num, power),
        den = int_pow(a.den, power),
    }
    return reduce(r)
}

expreal :: proc(x: f64, a: Rational) -> f64 {
    x_to_the_num := math.pow(x, f64(a.num))
    return math.pow(math.E, math.ln(x_to_the_num) / f64(a.den))
}

mul :: proc(a: Rational, b: Rational) -> Rational {
    r := Rational{a.num * b.num, a.den * b.den}
    return reduce(r)
}

sub :: proc(a: Rational, b: Rational) -> Rational {
    b := b
    b.num *= -1
    return add(a, b)
}

int_pow :: proc(a, b: int) -> int {
    if a == 0 && b == 0 { return 0 }
    if a == 1 || b == 0 { return 1 }

    a, b := a, b
    res := 1

    for b > 1 {
        if ((b & 1) == 1) {
            res *= a
            b -= 1
        }
        a *= a
        b >>= 1
    }

    return res * a
}
