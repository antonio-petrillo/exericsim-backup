import gleam/list

fn convert_tail_rec(number: Int, result: String) -> String {
  case number {
    0 -> result
    _ if number >= 1000 -> convert_tail_rec(number - 1000, result <> "M")
    _ if number >= 900 -> convert_tail_rec(number - 900, result <> "CM")
    _ if number >= 500 -> convert_tail_rec(number - 500, result <> "D")
    _ if number >= 400 -> convert_tail_rec(number - 400, result <> "CD")
    _ if number >= 100 -> convert_tail_rec(number - 100, result <> "C")
    _ if number >= 90 -> convert_tail_rec(number - 90, result <> "XC")
    _ if number >= 50 -> convert_tail_rec(number - 50, result <> "L")
    _ if number >= 40 -> convert_tail_rec(number - 40, result <> "XL")
    _ if number >= 10 -> convert_tail_rec(number - 10, result <> "X")
    _ if number >= 9 -> convert_tail_rec(number - 9, result <> "IX")
    _ if number >= 5-> convert_tail_rec(number - 5, result <> "V")
    _ if number >= 4 -> convert_tail_rec(number - 4, result <> "IV")
    _ -> convert_tail_rec(number - 1, result <> "I")
  }
}


pub fn convert(number: Int) -> String {
  convert_tail_rec(number, "")
}
