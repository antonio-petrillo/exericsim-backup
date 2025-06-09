import gleam/option.{type Option, Some, None}

pub fn two_fer(name: Option(String)) -> String {
  case name {
    None -> "One for you, one for me."
    Some(your_name) -> "One for " <> your_name <> ", one for me." 
  }
}
