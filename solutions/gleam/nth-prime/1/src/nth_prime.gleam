import gleam/int

pub fn prime(number: Int) -> Result(Int, Nil) {
  case number < 1 {
    True -> Error(Nil)
    False -> Ok(nth(number, 2))
  }
}

fn next_prime(n: Int) -> Int {
  case n {
    2 -> 3
    _ -> next_prime_gt_2(n + 2, 3)
  }
}

fn next_prime_gt_2(n: Int, curr: Int) -> Int {
  case n % curr, curr * curr > n {
    0, _ -> next_prime_gt_2(n + 2, 3)
    _, True -> n
    _, False -> next_prime_gt_2(n, curr + 2)
  }
}

fn nth(index: Int, curr: Int) -> Int {
  case index {
    1 -> curr
    _ -> nth(index - 1, next_prime(curr))
  }
}