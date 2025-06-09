import gleam/list
import gleam/int

pub type Command {
  Wink
  DoubleBlink
  CloseYourEyes
  Jump
  None
}

fn is_set(bit: Int, input: Int) {
  int.bitwise_and(input, int.bitwise_shift_left(1, bit)) != 0
}

fn reverse_if_5_is_set(commands: List(Command), message: Int) -> List(Command) {
  case is_set(4, message) {
    True -> list.reverse(commands)
    False -> commands
  }
}

pub fn commands(encoded_message: Int) -> List(Command) {
  list.range(0, 3)
  |> list.filter(fn(bit){is_set(bit, encoded_message)})
  |> list.map(fn(n) {
      case n {
        0 -> Wink
        1 -> DoubleBlink
        2 -> CloseYourEyes
        3 -> Jump
        _ -> None // unreachable
      }
    })
  |> reverse_if_5_is_set(encoded_message)
}
