import gleam/dict
import gleam/list

pub opaque type Set(t) {
  Set(impl: dict.Dict(t, Bool))
}

pub fn new(members: List(t)) -> Set(t) {
  members
  |> list.map(fn(member: t) -> #(t, Bool) { #(member, True) })
  |> dict.from_list
  |> Set
}

pub fn is_empty(set: Set(t)) -> Bool {
  dict.is_empty(set.impl)
}

pub fn contains(in set: Set(t), this member: t) -> Bool {
  dict.has_key(set.impl, member)
}

pub fn is_subset(first: Set(t), of second: Set(t)) -> Bool {
  first.impl
  |> dict.keys
  |> list.all(fn(element) { contains(second, element) })
}

pub fn disjoint(first: Set(t), second: Set(t)) -> Bool {
    first.impl
    |> dict.keys
    |> list.all(fn(element) { ! contains(second, element) })
}

pub fn is_equal(first: Set(t), to second: Set(t)) -> Bool {
  let same_size = dict.size(first.impl) == dict.size(second.impl)
  
  let is_in = fn(element: t) -> Bool {
    contains(second, element)
  }

  let same = first.impl
              |> dict.keys
              |> list.all(is_in)

  same && same_size
}

pub fn add(to set: Set(t), this member: t) -> Set(t) {
  set.impl
  |> dict.insert(member, True)
  |> Set
}

pub fn intersection(of first: Set(t), and second: Set(t)) -> Set(t) {
  first.impl
  |> dict.keys
  |> list.fold(dict.new(), fn(acc, el) {
      case contains(second, el) {
        True -> dict.insert(acc, el, True)
        False -> acc
      }
  })
  |> Set
}

pub fn difference(between first: Set(t), and second: Set(t)) -> Set(t) {
  first.impl
  |> dict.keys
  |> list.fold(dict.new(), fn(acc, el) {
      case contains(second, el) {
        True -> acc
        False -> dict.insert(acc, el, True)
      }
  })
  |> Set
}

pub fn union(of first: Set(t), and second: Set(t)) -> Set(t) {
  dict.merge(first.impl, second.impl)
  |> Set
}
