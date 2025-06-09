(ns roman-numerals
  (:require [clojure.string :refer [join]]))

(def thousands
  {1 "M"
   2 "MM"
   3 "MMM"})

(def hundreds
  {1 "C"
   2 "CC"
   3 "CCC"
   4 "CD"
   5 "D"
   6 "DC"
   7 "DCC"
   8 "DCCC"
   9 "CM"})

(def tens
  {1 "X"
   2 "XX"
   3 "XXX"
   4 "XL"
   5 "L"
   6 "LX"
   7 "LXX"
   8 "LXXX"
   9 "XC"})

(def digits
  {1 "I"
   2 "II"
   3 "III"
   4 "IV"
   5 "V"
   6 "VI"
   7 "VII"
   8 "VIII"
   9 "IX"})

(defn num->digits [num]
  (loop [result [] num num]
    (if (zero? num)
      result
      (recur (conj result (mod num 10)) (quot num 10)))))
;; note that the digits are in reverse order.

(defn numerals [num]
  ((comp (partial join "") reverse)
    (map
     #(get %2 %1 "")
     (num->digits num)
     [digits tens hundreds thousands])))

;; I think that the numerals function is quite interesting,
;; but I really don't like how I made the translation from number to roman
