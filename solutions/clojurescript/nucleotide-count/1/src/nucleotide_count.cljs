(ns nucleotide-count)

(defn count-of-nucleotide-in-strand [nucleotide dna-strand]
    (if (or (re-find #"[^ACTG]" dna-strand)
            (not (#{\A \C \T \G} nucleotide)))
        (throw (js/Error. "pujaz"))
        (->> dna-strand
            (filter #(= % nucleotide))
            count)))

(defn nucleotide-counts [dna-strand]
    (if (re-find #"[^ACTG]" dna-strand)
        (throw (js/Error. "pujaz"))
        (merge {\A 0, \C 0, \T 0, \G 0} (frequencies dna-strand))))
