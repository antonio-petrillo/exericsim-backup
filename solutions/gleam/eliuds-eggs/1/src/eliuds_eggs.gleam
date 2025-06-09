import gleam/int

fn egg_count_tail_rec (number: Int, acc: Int) -> Int {
  case number {
    0 -> acc
    _ -> egg_count_tail_rec(int.bitwise_and(number, number - 1), acc + 1)
  }
}

pub fn egg_count(number: Int) -> Int {
  egg_count_tail_rec(number, 0)
}
