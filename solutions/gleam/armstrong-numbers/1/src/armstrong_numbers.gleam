import gleam/list
import gleam/int

fn digits(number: Int, accumulator: List(Int), size: Int) -> #(List(Int), Int) {
  case number {
    0 -> #(accumulator, size)
    _ -> digits(number / 10, [number % 10, ..accumulator], size + 1)
  }
}

fn to_the_nth_power(n: Int, exp: Int, acc: Int) -> Int {
  case exp {
    0 -> 1
    1 -> n * acc
    _ -> to_the_nth_power(n, exp - 1, acc * n)
  }
}

pub fn is_armstrong_number(number: Int) -> Bool {
  let #(digits, size) = digits(number, [], 0)

  digits
  |> list.map(fn(n) { to_the_nth_power(n, size, 1) })
  |> int.sum
  |> fn(n) { n == number }
}
