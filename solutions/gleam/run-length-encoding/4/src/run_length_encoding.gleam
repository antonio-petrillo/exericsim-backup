import gleam/function
import gleam/int
import gleam/list
import gleam/option.{type Option, Some}
import gleam/regex.{type Match}
import gleam/string

pub fn encode(plaintext: String) -> String {
    plaintext
    |> string.to_graphemes
    |> list.chunk(function.identity)
    |> list.map(fn(chunk) {
      let assert [char, ..] = chunk
      let len = list.length(chunk)
      case len {
        1 -> char
        _ -> { len |> int.to_string } <> char
      }
    })
    |> string.concat
}

pub fn decode(ciphertext: String) -> String {
  let assert Ok(scanner) = regex.from_string("(\\d+)?(\\D)")

  ciphertext
  |> regex.scan(scanner, _)
  |> list.map(decode_chunk)
  |> string.concat
}

fn decode_chunk(match: Match) -> String {
  case match.submatches {
    [Some(num_char), Some(char)] -> {
      let assert Ok(num) = int.parse(num_char)

      string.repeat(char, num)
    }
    [_, Some(char)] -> char
    _ -> panic
  }
}