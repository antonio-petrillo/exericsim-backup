(ns phone-number)

(defn number [num-string]
  (let [digits (apply str (filter #(Character/isDigit %) num-string))
        first-digit (first digits)
        first-exchange-digit (nth digits 3)
        len (count digits)]
    (cond (and (= 11 len)(= \1 first-digit)) (subs digits 1)
          (or
           (= \0 first-digit)
           (= \1 first-digit)
           (= \0 first-exchange-digit)
           (= \1 first-exchange-digit)
           (= 11 len)
           (= 9 len)) "0000000000"
          :else digits)))

(defn area-code [num-string]
  (apply str (take 3 (number num-string))))

(defn pretty-print [num-string]
  (let [number (number num-string)
        area (subs number 0 3)
        triplet (subs number 3 6)
        rest (subs number 6)]
    (str "(" area ") " triplet "-" rest)))
