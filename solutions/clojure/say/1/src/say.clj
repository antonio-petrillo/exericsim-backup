(ns say)

(def digit-prefix
  {0 "zero"
   1 "one"
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

(def special-prefix
  {11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(def postfix
  [" billion "
   " million "
   " thousand "
   ""])

(defn group-by-3
  ([num]
   (group-by-3 num ()))
  ([num acc]
   (if (< num 1000)
     (conj acc num)
     (recur (quot num 1000) (conj acc (mod num 1000))))))

(defn spell-tens
  [num]
  (cond (< 0 num 10) (get digit-prefix num)
        (< 10 num 20) (get special-prefix num)
        :else (let [prefix (get ten-prefix (quot num 10))
                    digit (get (dissoc digit-prefix 0) (mod num 10))
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

(defn get-postfix
  [num]
  (cond (= 4 num) postfix
        (= 3 num) (rest postfix)
        (= 2 num) (rest (rest postfix))
        :else (rest (rest (rest postfix)))))

(defn say-non-zero
  [num]
  (let [grouped (group-by-3 num)
        size (count grouped)
        postfix (get-postfix size)
        size (count (filter (complement zero?) grouped))
        postfix (take size postfix)]
  (clojure.string/trim (apply str (filter #(and (not-empty %)) (map str (map spell-hundred grouped) postfix))))))

(defn number
  ;; your code goes here
  [num]
  (cond (zero? num) (get digit-prefix num)
        (< 0 num 1000000000000) (say-non-zero num)
        :else (throw (IllegalArgumentException. "trillion is not supported."))))

;; This solution is extremely overcomplicated, but it's to late to simplify, tomorrow I will make another interactio

;; Still I quite like the idea that I used, it only need a little bit of polish.
