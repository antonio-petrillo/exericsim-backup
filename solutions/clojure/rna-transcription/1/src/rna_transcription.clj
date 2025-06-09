(ns rna-transcription)

(def nucleotides
  {\G \C,
   \C \G,
   \T \A,
   \A \U})

(defn to-rna [dna] ;; <- arglist goes here
  ;; your code goes here
  (if (re-find #"[^GCTA]" dna)
               (throw (AssertionError. "Invalid nucleotides."))
               (apply str (map #(nucleotides %) dna))))
