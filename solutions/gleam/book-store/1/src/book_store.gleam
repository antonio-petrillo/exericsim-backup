import gleam/dict.{type Dict}
import gleam/function
import gleam/int
import gleam/list
import gleam/option.{type Option, Some, None}

const base_price = 800.0

fn increment_in_dict(x : Option(Int)) -> Int {
  case x {
    None -> 1
    Some(n) -> n + 1
  }
}

fn decrement_in_dict(x : Option(Int)) -> Int {
  case x {
    None -> 0
    Some(n) -> n - 1
  }
}

fn balance(counts: Dict(Int, Int)) -> Dict(Int, Int) {
  let has_3 = dict.has_key(counts, 3)
  let has_5 = dict.has_key(counts, 5)

  // if there is a pack of 3 and a pack of 5 remove one of each and add two packs of 4
  case has_3 && has_5 {
    True -> {
      counts
      |> dict.update(4, increment_in_dict)
      |> dict.update(4, increment_in_dict)
      |> dict.update(3, decrement_in_dict)
      |> dict.update(5, decrement_in_dict)
      |> dict.filter(fn(_, v) { v > 0 })
    }
    False -> counts
  }
}

fn groups(books: List(Int), groups_count: Dict(Int, Int)) -> Dict(Int, Int) {
  let count = list.length(books)
  let remaining = list.map(books, fn(x) { x - 1 }) |> list.filter(fn(x) { x > 0 })

  let next = dict.update(groups_count, count, increment_in_dict) |> balance

  case books {
    [] -> groups_count
    _ -> groups(remaining, next)
  }
}

fn discount(count: Int) -> Float {
  case count {
    1 -> 1.0
    2 -> 0.95
    3 -> 0.9
    4 -> 0.8
    _ -> 0.75
  }
}

pub fn lowest_price(books: List(Int)) -> Float {
  books
  |> list.group(function.identity)
  |> dict.values
  |> list.map(list.length)
  |> groups(dict.new())
  |> dict.fold(0.0, fn(a, k, v) {
    a +. int.to_float(k * v) *. base_price *. discount(k)
    })
}
