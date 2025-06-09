import gleam/int

pub type Error {
  NonPositiveNumber
}

pub fn steps(number: Int) -> Result(Int, Error) {
  case number > 0 {
    True -> collatz(number, 0) |> Ok
    False -> Error(NonPositiveNumber)
  }
}

fn collatz(number: Int, steps: Int) -> Int {
   case number, int.is_even(number) {
      1, _ -> steps
      _, True -> collatz(number / 2, steps +1)
      _, False -> collatz(number * 3 + 1, steps + 1)
   }
}