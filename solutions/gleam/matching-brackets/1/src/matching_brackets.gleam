import gleam/string
import gleam/list

fn matching(in: String) -> String {
  case in {
    ")" -> "("
    "]" -> "["
    "}" -> "{"
    _ -> ""
  }
}

fn is_balanced(grapheme: List(String), accumulator: List(String)) -> Bool {
  case grapheme, accumulator {
    [], _ -> list.is_empty(accumulator)
    [head, ..rest], [] -> {
      case head {
        ")" | "]" | "}" -> False
        "(" | "[" | "{" -> is_balanced(rest, [head])
        _ -> is_balanced(rest, [])
      }
    }
    [head, ..rest], [top, ..stack] -> {
      case head {
        "(" | "[" | "{" -> is_balanced(rest, [head, ..accumulator])
        ")" | "]" | "}" -> case top == matching(head) {
            True -> is_balanced(rest, stack)
            False -> False
          }
        _ -> is_balanced(rest, accumulator)
      }
    }
  }
}

pub fn is_paired(value: String) -> Bool {
  value
  |> string.to_graphemes
  |> is_balanced([])
}
