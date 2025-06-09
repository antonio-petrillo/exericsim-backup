(ns protein-translation)

(defn translate-codon ;; <- arglist goes here
  [codon]
  (let [mapping { "AUG" "Methionine",

                 "UUU" "Phenylalanine",
                 "UUC" "Phenylalanine",

                 "UUA" "Leucine",
                 "UUG" "Leucine",

                 "UCU" "Serine",
                 "UCC" "Serine",
                 "UCA" "Serine",
                 "UCG" "Serine",

                 "UAU" "Tyrosine",
                 "UAC" "Tyrosine",

                 "UGU" "Cysteine",
                 "UGC" "Cysteine",

                 "UGG" "Tryptophan",

                 "UAA" "STOP",
                 "UAG" "STOP",
                 "UGA" "STOP",
                 }]
    (mapping codon)))

(defn translate-rna [rna] ;; <- arglist goes here
  (->> (partition 3 rna)
       (mapv #(translate-codon (apply str %)))
       (take-while #(not= "STOP" %))))
