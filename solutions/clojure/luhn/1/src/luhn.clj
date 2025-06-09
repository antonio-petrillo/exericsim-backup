(ns luhn
  (:require [clojure.string :as s]))

(defn luhn? [s]
  (if (<= (count s) 1)
    false
    (let [digits (map
                  (fn [value index]
                    (if (odd? index)
                      (if (> value 4)
                        (- (* 2 value) 9)
                        (* 2 value))
                      value))
          (reverse (map #(Integer/parseInt %) (re-seq #"\d" s))) (range))]
      (= 0 (mod (reduce + 0 digits) 10)))))

(defn valid? [s] ;; <- arglist goes here
  ;; your code goes here
  (let [s (s/trim s)]
    (if (every? #(or (Character/isDigit %) (Character/isSpace %)) s)
      (luhn? s)
      false)))
