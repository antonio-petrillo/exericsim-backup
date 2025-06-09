import gleam/string

pub fn hey(remark: String) -> String {
  let trimmed = string.trim(remark)
  let all_capitals = trimmed == string.uppercase(trimmed) && trimmed != string.lowercase(trimmed) // Neat!
  let is_question = string.ends_with(trimmed, "?")

  case all_capitals, is_question, trimmed {
    _, _, "" -> "Fine. Be that way!"
    True, True, _ -> "Calm down, I know what I'm doing!"
    True, False, _ -> "Whoa, chill out!"
    False, True, _ -> "Sure."
    _, _, _ -> "Whatever."
  }
}
