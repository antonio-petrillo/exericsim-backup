(ns hexadecimal)

(def power-of-16 (iterate (partial * 16) 1))

(defn str->value [str]
  (get {"1" 1, "2" 2, "3" 3, "4" 4, "5" 5,
        "6" 6, "7" 7, "8" 8, "9" 9, "0" 0,
        "a" 10, "b" 11, "c" 12, "d" 13, "e" 14, "f" 15} str))

(defn hex-to-int [hex]
  (if (re-find #"[^0-9abcdef]" hex) 0
      (let [digits (reverse (re-seq #"[0-9abcdef]" hex))]
        (reduce + 0
                (mapv #(* (str->value %1) %2) digits power-of-16)))))
