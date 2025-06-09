import gleam/string
import gleam/list

pub fn slices(input: String, size: Int) -> Result(List(String), Error) {
  let len = string.length(input)
  case size, len , len < size {
    n, _, _ if n < 0 -> Error(SliceLengthNegative) 
    n, _, _ if n == 0 -> Error(SliceLengthZero)
    _, 0, _ -> Error(EmptySeries)
    _, _, True -> Error(SliceLengthTooLarge)
    _, _, _ -> partition(input, size, len, []) |> Ok
  }
}

fn partition(input: String, size: Int, length: Int, accumulator: List(String)) -> List(String) {
  case length < size {
    True -> list.reverse(accumulator)
    False -> partition(
      string.drop_left(input, 1),
      size,
      length - 1,
      [string.slice(input, 0, size), ..accumulator]
    )
  }
}

pub type Error {
  SliceLengthTooLarge
  SliceLengthNegative
  SliceLengthZero
  EmptySeries
}
