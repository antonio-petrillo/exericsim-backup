open Base

type nucleotide = A | C | G | T

let rec distance dna1 dna2 count =
  match (dna1, dna2) with
    | (x::xs, y::ys) -> distance xs ys (if x != y then count + 1 else count) 
    | _ -> count
    

let hamming_distance dna1 dna2 =
  match (List.is_empty dna1, List.is_empty dna2) with 
  | (true, false) -> Error "left strand must not be empty"
  | (false, true) -> Error "right strand must not be empty"
  | _ -> 
      if List.length dna1 <> List.length dna2 then 
        Error "left and right strands must be of equal length"
      else Ok (distance dna1 dna2 0)