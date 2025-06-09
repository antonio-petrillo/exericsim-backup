import gleam/int
import gleam/list

pub type Resistance {
  Resistance(unit: String, value: Int)
}

// https://exercism.org/tracks/gleam/exercises/resistor-color-trio/solutions/pf981
// maybe panic here is a bit to much
fn color_to_value(color: String) -> Result(Int, Nil) {
  case color {
    "black"  -> Ok(0)
    "brown"  -> Ok(1)
    "red"    -> Ok(2)
    "orange" -> Ok(3)
    "yellow" -> Ok(4)
    "green"  -> Ok(5)
    "blue"   -> Ok(6)
    "violet" -> Ok(7)
    "grey"   -> Ok(8)
    "white"  -> Ok(9)
    _        -> Error(Nil)
  }
}

pub fn label(colors: List(String)) -> Result(Resistance, Nil) {
  case list.map(colors, color_to_value) {
  [Ok(value1), Ok(value2), Ok(value3), ..] -> Ok(new_resistance(value1, value2, value3))
  _ -> Error(Nil)
  }
}

// I really miss an int/pow (I mean a power for integers)
// A better, faster, stronger pow (also from https://exercism.org/tracks/gleam/exercises/resistor-color-trio/solutions/pf981)
fn pow(x: Int, n: Int) -> Int {
  case n, int.is_even(n) {
    0, _ -> 1
    1, _ -> x
    _, True -> {
      let half = pow(x, n / 2)

      half * half
    }
    _, False -> {
      let half = pow(x, { n - 1 } / 2)

      half * half * x
    }
  }
}

fn new_resistance(val1: Int, val2: Int, val3: Int) -> Resistance {
  let value = { val1 * 10 + val2 } * pow(10, val3)

  // always as in https://exercism.org/tracks/gleam/exercises/resistor-color-trio/solutions/pf981
  case value {
   ohms if ohms < 1_000 -> Resistance(unit: "ohms", value: value)
   ohms if ohms < 1_000_000 -> Resistance(unit: "kiloohms", value: value / 1_000)
   ohms if ohms < 1_000_000_000 -> Resistance(unit: "megaohms", value: value / 1_000_000)
   _ -> Resistance(unit: "gigaohms", value: value / 1_000_000_000)

  }
}
