import gleam/list

fn accumulate(predicate: fn(t) -> Bool) {
  fn (accumulator: List(t), element: t) -> List(t) {
    case predicate(element) {
      True -> [element, ..accumulator]
      False -> accumulator
    }
  }
}

pub fn keep(list: List(t), predicate: fn(t) -> Bool) -> List(t) {
  list.fold_right(list, [], accumulate(predicate))
}

pub fn discard(list: List(t), predicate: fn(t) -> Bool) -> List(t) {
  keep(list, fn(t) {!predicate(t)})
}
