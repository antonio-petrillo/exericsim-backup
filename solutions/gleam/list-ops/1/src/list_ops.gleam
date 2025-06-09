import gleam/list

pub fn append(first first: List(a), second second: List(a)) -> List(a) {
    list.append(first, second)
}

pub fn concat(lists: List(List(a))) -> List(a) {
  list.concat(lists)
}

pub fn filter(list: List(a), function: fn(a) -> Bool) -> List(a) {
  list.filter(list, function)
}

pub fn length(list: List(a)) -> Int {
  list.length(list)
}

pub fn map(list: List(a), function: fn(a) -> b) -> List(b) {
  list.map(list, function)
}

pub fn foldl(
  over list: List(a),
  from initial: b,
  with function: fn(b, a) -> b,
) -> b {
   list.fold(list, initial, function)
}

pub fn foldr(
  over list: List(a),
  from initial: b,
  with function: fn(b, a) -> b,
) -> b {
  list.fold_right(list, initial, function)
}

pub fn reverse(list: List(a)) -> List(a) {
  list.reverse(list)
}

// I would have liked to write the solution from scratch, but now I know that I really hate gleam docs