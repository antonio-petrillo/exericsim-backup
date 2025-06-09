import gleam/int

pub type Item {
  Item(value: Int, weight: Int)
}

pub fn maximum_value(items: List(Item), maximum_weight: Int) -> Int {
  case items {
    [] -> 0
    [head, ..tail] -> {
      let sub_sol = maximum_value(tail, maximum_weight) // don't pick the current head
      case head.weight <= maximum_weight {
        True -> int.max(
            sub_sol,
            head.value + maximum_value(tail, maximum_weight - head.weight)
        )
        False -> sub_sol
      }
    }
  }
}
