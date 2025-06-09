module ProteinTranslation exposing (Error(..), proteins)


type Error
    = InvalidCodon

-- It would have been nice if the proteins where encoded into a type
-- like:

-- type Protein
--      = Methionine, Phenylalanine, ...

-- type Codon
--      = AUG, UUU, ...

-- type Mapping
--      = Codon Protein | InvalidCodon

-- and so on

codonToProtein : String -> String
codonToProtein codon =
    case codon of
        "AUG" -> "Methionine"

        "UUU" -> "Phenylalanine"
        "UUC" -> "Phenylalanine"

        "UUA" -> "Leucine"
        "UUG" -> "Leucine"

        "UCU" -> "Serine"
        "UCC" -> "Serine"
        "UCA" -> "Serine"
        "UCG" -> "Serine"

        "UAU" -> "Tyrosine"
        "UAC" -> "Tyrosine"

        "UGU" -> "Cysteine"
        "UGC" -> "Cysteine"

        "UGG" -> "Tryptophan"

        "UAA" -> "STOP"
        "UGA" -> "STOP"
        "UAG" -> "STOP"

        "" -> ""

        _ -> "ERR"


proteins : String -> Result Error (List String)
proteins strand =
    let
        triplet = String.left 3 strand
        protein = codonToProtein triplet
    in
        case protein of
            "ERR" ->
                Err InvalidCodon

            "STOP" ->
                Ok []

            "" ->
                Ok []

            _ ->
                let
                    accumulate = String.dropLeft 3 strand |> proteins
                in
                    case accumulate of
                        Err e -> Err e
                        Ok acc -> Ok (protein::acc)
