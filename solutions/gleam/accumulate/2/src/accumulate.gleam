// pub fn accumulate(list: List(a), fun: fn(a) -> b) -> List(b) {
//   case list {
//     [] -> []
//     [head, ..rest] -> [fun(head), ..accumulate(rest, fun)]
//   }
// }

fn foldr(list: List(a), acc: b, fun: fn(b, a) -> b) -> b {
  case list {
    [] -> acc
    [head, ..rest] -> fun(foldr(rest, acc, fun), head)
  }
}

pub fn accumulate(list: List(a), fun: fn(a) -> b) -> List(b) {
  foldr(list, [], fn(acc, el) {
    [fun(el), ..acc]
  })
}