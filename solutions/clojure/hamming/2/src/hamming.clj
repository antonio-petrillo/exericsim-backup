(ns hamming)

(defn distance [strand1 strand2] ; <- arglist goes here
  (when (= (count strand1) (count strand2))
    (->> (map = strand1 strand2)
         (filter false?)
         (count))))
