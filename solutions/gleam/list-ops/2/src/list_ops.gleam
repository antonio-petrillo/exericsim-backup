pub fn append(first first: List(a), second second: List(a)) -> List(a) {
  foldr(first, second, fn(acc, el) { [el, ..acc] })
}

pub fn concat(lists: List(List(a))) -> List(a) {
  //  foldl(lists, [], append)

  // Should be better in practice, would be fun to benchmark
  foldr(lists, [], fn(acc, el) { append(el, acc) })
}

pub fn filter(list: List(a), function: fn(a) -> Bool) -> List(a) {
  let keep_fn = fn(acc, el) {
    case function(el) {
      True ->  [el, ..acc]
      False -> acc
    }
  }
  foldr(list,  [], keep_fn)
}

pub fn length(list: List(a)) -> Int {
  foldl(list, 0, fn(acc, _) { acc + 1 })
}

pub fn map(list: List(a), function: fn(a) -> b) -> List(b) {
  foldr(list, [], fn(acc, el) { [function(el), ..acc] } )
}

pub fn foldl(
  over list: List(a),
  from initial: b,
  with function: fn(b, a) -> b,
) -> b {
  case list {
    [head, ..rest] -> foldl(rest, function(initial, head), function)
    _ -> initial
  }
}

pub fn foldr(
  over list: List(a),
  from initial: b,
  with function: fn(b, a) -> b,
) -> b {
  case list {
    [head, ..rest] -> foldr(rest, initial, function) |> function(head)
    [] -> initial
  }
}

pub fn reverse(list: List(a)) -> List(a) {
  foldl(list, [], fn(acc, el) { [el ,..acc] })
}
