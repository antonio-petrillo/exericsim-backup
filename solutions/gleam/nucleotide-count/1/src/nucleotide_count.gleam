import gleam/dict.{type Dict}
import gleam/string
import gleam/list.{Continue, Stop}
import gleam/option.{Some, None}

fn update(acc: Result(Dict(String, Int), Nil), key: String) -> Result(Dict(String, Int), Nil) {
  let assert Ok(d) = acc 
  dict.upsert(d, key, fn(el) {
    case el {
      Some(n) -> n + 1
      None -> 1
    }
  })
  |> Ok
}

pub fn nucleotide_count(dna: String) -> Result(Dict(String, Int), Nil) {
  let default = dict.from_list([#("A", 0), #("C", 0), #("G", 0), #("T", 0)])
  dna
  |> string.to_graphemes 
  |> list.fold_until(Ok(default), fn(acc, a) {
    case a {
    "A" | "a" -> Continue(update(acc, "A"))
    "C" | "c" -> Continue(update(acc, "C"))
    "G" | "g" -> Continue(update(acc, "G"))
    "T" | "t" -> Continue(update(acc, "T"))
    _ -> Stop(Error(Nil))
    }
  })
}
