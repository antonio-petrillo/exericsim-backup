import gleam/list

pub type Error {
  InvalidBase(Int)
  InvalidDigit(Int)
}

fn to_base_10(digits: List(Int), input_base: Int) -> Int {
  list.fold(digits, 0, fn(sum, digit) { sum * input_base + digit })
}

fn to_out_base(num: Int, out_base: Int, acc: List(Int)) -> List(Int) {
  case num {
    0 -> case list.is_empty(acc) { 
           True -> [0]
           False -> acc
    }
    1 -> [1, ..acc]
    _ -> to_out_base(num / out_base, out_base, [num % out_base, .. acc])
  }
}

pub fn rebase(
  digits digits: List(Int),
  input_base input_base: Int,
  output_base output_base: Int,
) -> Result(List(Int), Error) {
   case input_base < 2, output_base < 2 {
      True, _ -> Error(InvalidBase(input_base))
      _, True -> Error(InvalidBase(output_base))
      _, _ -> {
          case list.find(digits, fn(digit) {digit < 0 || digit >= input_base}) {
            Ok(digit) -> Error(InvalidDigit(digit))
            _ -> digits
                  |> to_base_10(input_base)
                  |> to_out_base(output_base, [])
                  |> Ok
          }
      }
   }
}
