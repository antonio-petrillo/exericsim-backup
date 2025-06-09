import gleam/list

fn factors_tail(value: Int, factor: Int, acc: List(Int)) -> List(Int) {
  case value, value % factor {
    1, _ -> list.reverse(acc)
    _, 0 -> factors_tail(value / factor, factor, [factor, ..acc])
    _, _ -> factors_tail(value, factor + 1, acc)
  }
}

pub fn factors(value: Int) -> List(Int) {
  factors_tail(value, 2, [])
}
