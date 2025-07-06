package complexnumbers

import "math"

type Number struct {
	A float64
	B float64
}

func (n Number) Real() float64 {
	return n.A
}

func (n Number) Imaginary() float64 {
	return n.B
}

func (n1 Number) Add(n2 Number) Number {
	return Number{n1.A + n2.A, n1.B + n2.B}
}

func (n1 Number) Subtract(n2 Number) Number {
	return Number{n1.A - n2.A, n1.B - n2.B}
}

func (n1 Number) Multiply(n2 Number) Number {
	return Number{n1.A*n2.A - n1.B*n2.B, n1.A*n2.B + n1.B*n2.A}
}

func (n Number) Times(factor float64) Number {
	return Number{n.A * factor, n.B * factor}
}

func (n1 Number) Divide(n2 Number) Number {
	denominator := n2.A*n2.A + n2.B*n2.B
	return Number{(n1.A*n2.A + n1.B*n2.B) / denominator, (n1.B*n2.A - n1.A*n2.B) / denominator}
}

func (n Number) Conjugate() Number {
	return Number{n.A, -n.B}
}

func (n Number) Abs() float64 {
	return math.Hypot(n.A, n.B)
}

func (n Number) Exp() Number {
	a := math.Exp(n.A)
	return Number{a * math.Cos(n.B), a * math.Sin(n.B)}
}
