import gleam/list

pub fn primes_up_to(upper_bound: Int) -> List(Int) {
  case upper_bound {
    n if n <= 1 -> []
    2 -> [2]
    _ -> sieve([2, ..list.range(3, upper_bound)], [])
  }
}

fn div_by(n: Int) -> fn(Int) -> Bool {
  fn(input: Int) -> Bool {
    input % n != 0
  }
}

fn sieve(candidates: List(Int), primes: List(Int)) -> List(Int) {
  case candidates {
    [] -> list.reverse(primes)
    [head, ..rest] -> sieve(list.filter(rest, div_by(head)), [head, ..primes])
  }
}
