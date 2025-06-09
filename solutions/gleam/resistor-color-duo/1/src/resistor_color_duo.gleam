import gleam/list

pub type Color {
  Black
  Brown
  Red
  Orange
  Yellow
  Green
  Blue
  Violet
  Grey
  White
}

fn dispatch(color: Color) -> Int {
  case color {
    Black -> 0
    Brown -> 1
    Red -> 2
    Orange -> 3
    Yellow -> 4
    Green -> 5
    Blue -> 6
    Violet -> 7
    Grey -> 8
    White -> 9
  }
}

pub fn value(colors: List(Color)) -> Result(Int, Nil) {
  case list.length(colors) < 2 {
    True -> Error(Nil)
    False -> colors
             |> list.take(2)
             |> list.fold(0, fn(sum, color) {sum * 10 + dispatch(color)})
             |> Ok
  }
}
