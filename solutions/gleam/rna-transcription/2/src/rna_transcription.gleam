import gleam/string
import gleam/list
import gleam/result

pub fn to_rna(dna: String) -> Result(String, Nil) {
  dna
  |> string.to_graphemes
  |> list.try_map(translate)
  |> result.map(string.concat)
}

fn translate(nucleotide: String) -> Result(String, Nil) {
  case nucleotide {
    "A" -> Ok("U")
    "C" -> Ok("G")
    "T" -> Ok("A")
    "G" -> Ok("C")
    _ -> Error(Nil)
  }
}
