import gleam/list

fn factors_tail(value: Int, factor: Int, acc: List(Int)) -> List(Int) {
  case value {
    1 -> list.reverse(acc)
    _ -> {
      case value % factor {
        0 -> factors_tail(value / factor, factor, [factor, ..acc])
        _ -> factors_tail(value, factor + 1, acc)
      }
    }
  }
}

pub fn factors(value: Int) -> List(Int) {
  factors_tail(value, 2, [])
}
