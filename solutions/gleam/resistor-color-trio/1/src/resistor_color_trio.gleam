import gleam/list
import gleam/result

pub type Resistance {
  Resistance(unit: String, value: Int)
}

fn color_to_value(color: String) -> Int {
  case color {
    "black"  -> 0
    "brown"  -> 1
    "red"    -> 2
    "orange" -> 3
    "yellow" -> 4
    "green"  -> 5
    "blue"   -> 6
    "violet" -> 7
    "grey"   -> 8
    "white"  -> 9
    _        -> panic
  }
}

pub fn label(colors: List(String)) -> Result(Resistance, Nil) {
  case list.map(colors, color_to_value) {
  [value1, value2, value3, ..] -> Ok(new_resistance(value1, value2, value3))
  _ -> Error(Nil)
  }
}

// I really miss an int/pow (I mean a power for integers)
fn pow(x: Int, n: Int) -> Int {
  case n {
    0 -> 1
    1 -> x
    _ -> x * pow(x, n - 1)
  }
}

fn new_resistance(val1: Int, val2: Int, val3: Int) -> Resistance {
  let value = { val1 * 10 + val2 } * pow(10, val3)

  case value {
   ohms if ohms < 1000 -> Resistance(unit: "ohms", value: value)
   ohms if ohms < 1000000 -> Resistance(unit: "kiloohms", value: value / 1000)
   ohms if ohms < 1000000000 -> Resistance(unit: "megaohms", value: value / 1000000)
   _ -> Resistance(unit: "gigaohms", value: value / 1000000000)

  }
}
