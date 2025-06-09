import gleam/regex
import gleam/option.{type Option, Some}

pub fn is_valid_line(line: String) -> Bool {
  let assert Ok(valid) = regex.from_string("^\\[DEBUG|INFO|WARNING|ERROR\\].*")

  regex.check(valid, line)
}

pub fn split_line(line: String) -> List(String) {
  let assert Ok(splitter) = regex.from_string("<[~*=-]*>")
  regex.split(splitter, line)
}

pub fn tag_with_user_name(line: String) -> String {
  let assert Ok(tag) = regex.from_string("User\\s+([^\\s]+)")
  case regex.scan(tag, line) {
  [regex.Match(_, [Some(user)])] -> "[USER] " <> user <> " " <> line
  _ -> line   
  }
}
