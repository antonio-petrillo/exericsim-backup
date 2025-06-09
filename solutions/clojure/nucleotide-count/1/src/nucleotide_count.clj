(ns nucleotide-count)

(defn count-of-nucleotide-in-strand [nucleotide strand] ;; <- Arglist goes here
  (if (or (re-find #"[^ACGT]" strand)
          (not (#{\A \C \G \T} nucleotide)))
    (throw (IllegalArgumentException. "Unknown nucleotides"))
    (->> strand
     (filter #(= % nucleotide))
     (count))))


(defn nucleotide-counts [strand] ;; <- Arglist goes here
  (if (re-find #"[^ACGT]" strand)
    (throw (IllegalArgumentException. "Unknown nucleotides"))
    (merge {\A 0, \C 0, \G 0, \T 0}
           (frequencies strand))))
