(ns sum-of-multiples)

(defn divisible-by? [num divisors]
  (loop [divisors divisors]
    (cond (empty? divisors) false
          (zero? (mod num (first divisors))) true
          :else (recur (rest divisors)))))

(defn sum-of-multiples [factors up-to] ;; <- arglist goes here
  ;; your code goes here
  (let [numbers (range 1 up-to)]
    (reduce + 0 (filter #(divisible-by? % factors) numbers))))
