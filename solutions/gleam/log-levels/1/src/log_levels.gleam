import gleam/string as str

pub fn message(log_line: String) -> String {
  let msg = case log_line {
    "[ERROR]:" <> msg -> msg
    "[WARNING]:" <> msg -> msg
    "[INFO]:" <> msg -> msg
    _ -> log_line
  }

  str.trim(msg)
}

pub fn log_level(log_line: String) -> String {
  case log_line {
    "[ERROR]:" <> _ -> "error"
    "[WARNING]:" <> _ -> "warning"
    "[INFO]:" <> _ -> "info"
    _ -> "None"
  }
}

pub fn reformat(log_line: String) -> String {
  let level = log_level(log_line)
  let msg = message(log_line)

  msg <> " (" <> level <> ")"
}
