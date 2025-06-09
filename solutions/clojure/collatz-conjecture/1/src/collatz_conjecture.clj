(ns collatz-conjecture)

(defn collatz [num] ;; <- arglist goes here
  (if (<= num 0)
    (throw (IllegalArgumentException. "Invalid parameter."))
    (loop [num num step 0]
      (if (= 1 num)
        step
        (recur (if (even? num) (quot num 2) (inc (* 3 num))) (inc step))))))
