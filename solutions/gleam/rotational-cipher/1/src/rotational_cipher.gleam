import gleam/string
import gleam/list

pub fn rotate(shift_key: Int, text: String) -> String {
  let codepoints = text
      |> string.to_utf_codepoints
      |> list.map(string.utf_codepoint_to_int)

  let shift = shift_by(shift_key)

  codepoints
  |> list.map(shift)
  |> list.map(fn(codepoint: Int) -> UtfCodepoint {
    let assert Ok(utf_codepoint) = string.utf_codepoint(codepoint)

    utf_codepoint
  })
  |> string.from_utf_codepoints
}

fn shift_by(shift_key: Int) -> fn(Int) -> Int {
  fn (codepoint: Int) {
    case codepoint {
      n if n >= 97 && n <= 122 -> {{ n - 97 } + shift_key} % 26 + 97
      n if n >= 65 && n <= 90 -> {{ n - 65 } + shift_key} % 26 + 65
      _ -> codepoint
    }
  }
}