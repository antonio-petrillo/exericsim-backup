(ns grains)

(defn square [cell]
  ;; (bit-shift-left 1 (dec 1)) ;; this number is a big boy
  (.shiftLeft BigInteger/ONE (dec cell)))

;; (map square (range 1 8))

;; (square 64)

(defn total []
  (dec (square 65)))
