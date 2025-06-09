import gleam/list.{type ContinueOrStop, Stop, Continue}
import gleam/result
import gleam/string

pub fn proteins(rna: String) -> Result(List(String), Nil) {
  rna
  |> string.to_graphemes
  |> list.sized_chunk(3)
  |> list.map(string.concat)
  |> list.map(codon_to_protein)
  |> list.take_while(fn(protein) {
      case protein {
        Ok("STOP") -> False
        _ -> True
      }
    })
  |> result.all
}

fn codon_to_protein(codon: String) -> Result(String, Nil) {
  case codon {
    "AUG"	-> Ok("Methionine")
    "UUU" | "UUC" -> Ok("Phenylalanine")
    "UUA" | "UUG"-> Ok("Leucine")
    "UCU" | "UCC" | "UCA" | "UCG" -> Ok("Serine")
    "UAU" | "UAC" -> Ok("Tyrosine")
    "UGU" | "UGC" -> Ok("Cysteine")
    "UGG" -> Ok("Tryptophan")
    "UAA" | "UAG" | "UGA" -> Ok("STOP")
    _ -> Error(Nil)
  }
}
