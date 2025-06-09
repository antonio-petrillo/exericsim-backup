import gleam/dict
import gleam/list
import gleam/function
import gleam/string

pub fn is_isogram(phrase phrase: String) -> Bool {
  phrase
  |> string.lowercase
  |> string.to_utf_codepoints
  |> list.filter(fn(str) { 
      let codepoint = string.utf_codepoint_to_int(str)
      codepoint >= 97 && codepoint <= 122
  })
  |> list.group(function.identity)
  |> dict.values
  |> list.all(fn (x) { list.length(x) == 1 } )
}
