import gleam/string

fn reverse_tail(graphemes: List(String), acc: List(String)) -> String {
  case graphemes {
    [] -> string.join(acc, "")
    [char, ..chars] -> reverse_tail(chars, [char, ..acc])
  }
}

pub fn reverse(value: String) -> String {
   reverse_tail(string.to_graphemes(value), [])
}
