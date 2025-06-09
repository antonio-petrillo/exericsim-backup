import gleam/string
import gleam/list
import gleam/set

pub fn is_pangram(sentence: String) -> Bool {
  sentence
  |> string.lowercase
  |> string.to_utf_codepoints
  |> list.filter(fn(codepoint) {
    let int_codepoint = string.utf_codepoint_to_int(codepoint)
    int_codepoint >= 97 && int_codepoint <= 122
  })
  |> list.fold(set.new(), set.insert)
  |> set.size
  |> fn(size) { size == 26 }
}
