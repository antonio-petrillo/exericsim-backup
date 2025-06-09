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
      case chunk {
        [x] -> x
        [head, .._] -> { list.length(chunk) |> int.to_string } <> head
        [] -> panic
      }
    })
    |> string.concat
}

pub fn decode(ciphertext: String) -> String {
  let assert Ok(scanner) = regex.from_string("([0-9]+)([^0-9])|([^0-9]+)")

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
    [_, _, Some(chars)] -> chars
    _ -> panic
  }
}