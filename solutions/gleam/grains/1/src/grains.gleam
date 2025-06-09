import gleam/int

pub type Error {
  InvalidSquare
}

pub fn square(square: Int) -> Result(Int, Error) {
  case square {
    n if n < 1 -> Error(InvalidSquare)
    n if n > 64 -> Error(InvalidSquare)
    _ -> Ok(int.bitwise_shift_left(1, square - 1))
  }
}

pub fn total() -> Int {
  int.bitwise_shift_left(1, 64) - 1
}
