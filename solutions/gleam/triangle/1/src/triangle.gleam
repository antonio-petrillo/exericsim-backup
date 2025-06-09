fn valid_sides(a: Float, b: Float, c: Float) -> Bool {
  a >. 0.0 && b >. 0.0 && c >. 0.0
}

fn inequality(a: Float, b: Float, c: Float) -> Bool {
  a +. b >=. c && a +. c >=. b && b +. c >=. a
}

pub fn equilateral(a: Float, b: Float, c: Float) -> Bool {
  inequality(a, b, c)
  && valid_sides(a, b, c)
  && a == b && b == c
}

pub fn isosceles(a: Float, b: Float, c: Float) -> Bool {
  inequality(a, b, c)
  && valid_sides(a, b, c)
  && { a == b || a == c || b == c }
}

pub fn scalene(a: Float, b: Float, c: Float) -> Bool {
  inequality(a, b, c)
  && valid_sides(a, b, c)
  && a != b && b != c && a != c
}
