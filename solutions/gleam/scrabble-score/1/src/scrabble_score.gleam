import gleam/int
import gleam/list
import gleam/string

pub fn score(word: String) -> Int {
  word
  |> string.uppercase
  |> string.to_graphemes
  |> list.map(point)
  |> int.sum
}

fn point(letter: String) {
  case letter {
    "A" | "E" | "I" | "O" | "U" | "L" | "N" | "R" | "S" | "T" -> 1 
    "D" | "G" -> 2
    "B" | "C" | "M" | "P" -> 3
    "F" | "H" | "V" | "W" | "Y" -> 4
    "K" -> 5
    "J" | "X" -> 8
    "Q" | "Z" -> 10
    _ -> 0
  }
}
