import gleam/list
import gleam/int
import gleam/float
import gleam/order.{type Order, Eq, Gt, Lt}

pub type Classification {
  Perfect
  Abundant
  Deficient
}

pub type Error {
  NonPositiveInt
}

pub fn classify(number: Int) -> Result(Classification, Error) {
  case number, number > 0 {
    1, _ -> Ok(Deficient)
    _, True -> compute(number) |> Ok
    _, False -> Error(NonPositiveInt)
  }
}

fn compute(number: Int) -> Classification {
  let sum = { number / 2}
            |> list.range(1, _)
            |> list.filter(fn(x) { number % x == 0})
            |> int.sum

  case int.compare(sum, number) {
    Eq -> Perfect
    Gt -> Abundant
    Lt -> Deficient
  }
}
