import gleam/list
import gleam/int

pub fn sum(factors factors: List(Int), limit limit: Int) -> Int {
  list.range(0, limit - 1)
  |> list.filter(fn(n) { list.any(factors, fn(factor) { factor > 0 && n % factor == 0 }) }) // I don't like the double lambda function here
  |> int.sum()
}
