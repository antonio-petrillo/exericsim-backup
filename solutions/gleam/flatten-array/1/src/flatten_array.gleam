import gleam/list

pub type NestedList(a) {
  Null
  Value(a)
  List(List(NestedList(a)))
}

pub fn flatten(nested_list: NestedList(a)) -> List(a) {
  flatten_tail(nested_list, [])
}

fn flatten_tail(nested: NestedList(a), acc: List(a)) -> List(a) {
  case nested {
    Null -> acc
    Value(x) -> [x, ..acc]
    List(inner) -> list.fold_right(inner, acc, fn(a,b) { flatten_tail(b, a) })
  }
}