import gleam/int

pub type Item {
  Item(value: Int, weight: Int)
}

pub fn maximum_value(items: List(Item), maximum_weight: Int) -> Int {
  case items {
    [] -> 0
    [head, ..tail] if head.weight <= maximum_weight -> int.max(
            maximum_value(tail, maximum_weight),
            head.value + maximum_value(tail, maximum_weight - head.weight)
    )
    [_, ..tail] -> maximum_value(tail, maximum_weight)
  }
}
