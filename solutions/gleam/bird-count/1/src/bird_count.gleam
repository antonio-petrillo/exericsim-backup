// personal rule, don't use gleam/list and gleam/int for this exercise

pub fn today(days: List(Int)) -> Int {
  case days {
    [] -> 0
    [head, .._] -> head
  }
}

pub fn increment_day_count(days: List(Int)) -> List(Int) {
  case days {
    [] -> [1]
    [head, ..rest] -> [head + 1, ..rest]
  }
}

pub fn has_day_without_birds(days: List(Int)) -> Bool {
  case days {
    [] -> False
    [0, .._] -> True
    [_, ..rest] -> has_day_without_birds(rest)
  }
}

fn total_tail_rec(days: List(Int), sum: Int) -> Int {
  case days {
    [] -> sum
    [head, ..rest] -> total_tail_rec(rest, sum + head)
  }
}

pub fn total(days: List(Int)) -> Int {
  total_tail_rec(days, 0)
}

fn busy_days_tail_rec(days: List(Int), count: Int) -> Int {
  case days {
    [] -> count
    [n, ..rest] if n >= 5 -> busy_days_tail_rec(rest, count + 1)
    [_, ..rest] -> busy_days_tail_rec(rest, count)
  }
}

pub fn busy_days(days: List(Int)) -> Int {
  busy_days_tail_rec(days, 0)
}
