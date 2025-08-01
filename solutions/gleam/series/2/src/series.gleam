import gleam/string
import gleam/list

pub fn slices(input: String, size: Int) -> Result(List(String), Error) {
  let len = string.length(input)
  case input, size {
    "", _ -> Error(EmptySeries) 
    _, n if n == 0 -> Error(SliceLengthZero)
    _, n if n > len -> Error(SliceLengthTooLarge)
    _, n if n < 0 -> Error(SliceLengthNegative)
    _, _ -> input
      |> string.to_graphemes
      |> list.window(size)
      |> list.map(string.concat)
      |> Ok
  }
}

pub type Error {
  SliceLengthTooLarge
  SliceLengthNegative
  SliceLengthZero
  EmptySeries
}
