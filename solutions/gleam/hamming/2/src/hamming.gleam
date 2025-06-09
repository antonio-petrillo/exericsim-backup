import gleam/string
import gleam/list
import gleam/result

pub fn distance(strand1: String, strand2: String) -> Result(Int, Nil) {
  let l1 = string.to_graphemes(strand1)
  let l2 = string.to_graphemes(strand2)

  list.strict_zip(l1, l2)
  |> result.map(fn(zipped) {
    list.fold(zipped, 0, fn(acc, pair) {
      case pair.0 != pair.1 {
        True -> acc + 1
        _ -> acc
      }
    })
  })
  
}
