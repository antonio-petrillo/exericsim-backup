import gleam/string
import gleam/list

pub fn distance(strand1: String, strand2: String) -> Result(Int, Nil) {
  let l1 = string.to_graphemes(strand1)
  let l2 = string.to_graphemes(strand2)

  case list.strict_zip(l1, l2) {
    Ok(zipped) -> {
      list.fold(zipped, 0, fn(acc, pair) {
        case pair.0 != pair.1 {
          True -> acc + 1
          False -> acc
        }
      })
      |> Ok
    }
    _ -> Error(Nil)
  }
  
}
