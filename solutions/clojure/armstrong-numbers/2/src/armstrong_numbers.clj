(ns armstrong-numbers)

(defn num->digits [num]
  (map #(-> % str Integer/parseInt) (seq (str num))))

(defn armstrong? [num] ;; <- arglist goes here
  (let [values (num->digits num)
        pow-level (count values)
        sum (reduce + 0 (map #(.pow (biginteger %) pow-level)  values))]
    (= num sum)))
