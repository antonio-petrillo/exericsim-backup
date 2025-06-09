(ns pangram)

(defn pangram? [s] ;; <- arglist goes here
  ;; your code goes here
  ;; (filter zero? (frequencies s))
  (= 26
     (count (reduce
      (fn [acc char]
        (if (Character/isLetter char)
          (let [char (Character/toLowerCase char)]
            (conj acc char))
          acc))
      #{} s))))
