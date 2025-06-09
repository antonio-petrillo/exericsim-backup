import gleam/string
import gleam/list
import gleam/int

pub fn is_pangram(sentence: String) -> Bool {
  sentence
  |> string.lowercase
  |> string.to_utf_codepoints
  |> list.map(string.utf_codepoint_to_int)
  |> list.filter(fn(codepoint) {
    codepoint >= 97 && codepoint <= 122
  })
  |> list.map(fn(x) { x - 97 })
  |> list.fold(0, fn(acc, el) {
    int.bitwise_or(acc, int.bitwise_shift_left(1, el))
  })
  |> fn(letters) { letters == 0x03FFFFFF }
}
