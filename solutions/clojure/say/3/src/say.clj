(ns say)

(def digit-prefix
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"})

(def ten-prefix
  {1 "ten"
   2 "twenty"
   3 "thirty"
   4 "forty"
   5 "fifty"
   6 "sixty"
   7 "seventy"
   8 "eighty"
   9 "ninety"})

(def special-case
  {11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(defn group-by-3
  ([num]
   (group-by-3 num []))
  ([num acc]
   (if (< num 1000)
     (conj acc num)
     (recur (quot num 1000) (conj acc (mod num 1000))))))


(defn spell-tens
  [num]
  (cond (< 0 num 10) (get digit-prefix num)
        (< 10 num 20) (get special-case num)
        :else (let [prefix (get ten-prefix (quot num 10))
                    digit (get digit-prefix (mod num 10))
                    digit (if-not (nil? digit) (str "-" digit))]
                (str prefix digit))))

(defn spell-hundred
  [num]
  (let [hundred (quot num 100)
        tens (spell-tens (mod num 100))]
    (if-not (zero? hundred)
      (if-not (= "zero" tens)
        (str (get digit-prefix hundred) " hundred " tens)
        (str (get digit-prefix hundred) " hundred "))
      tens)))

(defn say-non-zero
  [grouped]
  (loop [remaining grouped
         result ""
         postfix '("" "thousand" "million" "billion")]
    (if (empty? remaining)
      (clojure.string/trim result)
      (recur
       (rest remaining)
       ((fn [acc elem postfix]
          (let [s (spell-hundred elem)]
            (if-not (empty? s)
              (str s " " postfix " " acc)
              acc)))
        result
        (first remaining)
        (first postfix))
       (rest postfix)))))

(defn number
  ;; your code goes here
  [num]
  (cond (zero? num) "zero"
        (< 0 num 1000000000000) (say-non-zero (group-by-3 num))
        :else (throw (IllegalArgumentException. "trillion is not supported."))))
