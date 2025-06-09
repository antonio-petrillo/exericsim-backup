import gleam/int
import gleam/string

pub opaque type Clock {
  Clock(hour: Int, minute: Int)
}

pub fn create(hour hour: Int, minute minute: Int) -> Clock {
  let assert Ok(m) = int.modulo(minute, 60)
  let assert Ok(carry) = int.floor_divide(minute, 60)
  let assert Ok(h) = int.modulo(hour + carry, 24)

  Clock(h, m)
}

pub fn add(clock: Clock, minutes minutes: Int) -> Clock {
  let Clock(h, m) = clock
  create(h, m + minutes)
}

pub fn subtract(clock: Clock, minutes minutes: Int) -> Clock {
  let Clock(h, m) = clock
  create(h, m - minutes)
}
 
pub fn display(clock: Clock) -> String {
  let Clock(h, m) = clock

  string.pad_left(int.to_string(h), 2, "0") <> ":" <> string.pad_left(int.to_string(m), 2, "0")  
}
