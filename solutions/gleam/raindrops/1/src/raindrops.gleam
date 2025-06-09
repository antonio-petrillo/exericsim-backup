import gleam/list
import gleam/string
import gleam/int

pub fn convert(number: Int) -> String {
  let pling = case number % 3 {
    0 -> "Pling"
    _ -> ""
  }
  let plong = case number % 5 {
    0 -> "Plang"
    _ -> ""
  }
  let plang = case number % 7 {
    0 -> "Plong"
    _ -> ""
  }

  case list.any([pling, plong, plang], fn(s) { ! string.is_empty(s)}) {
    True -> pling <> plong <> plang
    False -> int.to_string(number)
  }

}
