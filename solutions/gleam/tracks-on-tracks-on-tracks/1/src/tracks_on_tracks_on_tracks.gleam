pub fn new_list() -> List(String) {
  []
}

pub fn existing_list() -> List(String) {
  ["Gleam", "Go", "TypeScript"]
}

pub fn add_language(languages: List(String), language: String) -> List(String) {
  [language, ..languages]
}

pub fn count_languages(languages: List(String)) -> Int {
  count(languages, 0)
}

fn count(l: List(a), accumulator: Int) -> Int {
  case l {
    [_, ..rest] -> count(rest, accumulator + 1)
    _ -> accumulator
  }
}

pub fn reverse_list(languages: List(String)) -> List(String) {
  reverse(languages, [])
}

fn reverse(l: List(a), reversed: List(a)) -> List(a){
  case l {
    [head, ..rest] -> reverse(rest, [head, ..reversed])
    [] -> reversed
  }
}

pub fn exciting_list(languages: List(String)) -> Bool {
  case languages {
    ["Gleam", ..] | [_, "Gleam", _] | [_, "Gleam"] -> True
    _ -> False
  }
}
