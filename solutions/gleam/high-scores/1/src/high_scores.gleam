import gleam/list
import gleam/int
import gleam/order

pub fn scores(high_scores: List(Int)) -> List(Int) {
  high_scores
}

pub fn latest(high_scores: List(Int)) -> Result(Int, Nil) {
  list.last(high_scores)
}

pub fn personal_best(high_scores: List(Int)) -> Result(Int, Nil) {
  case high_scores {
    [] -> Error(Nil)
    [head, ..rest] -> list.fold(rest, head, int.max) |> Ok
  }
}

pub fn personal_top_three(high_scores: List(Int)) -> List(Int) {
  high_scores
  |> list.sort(int.compare |> order.reverse)
  |> list.take(3)
}
