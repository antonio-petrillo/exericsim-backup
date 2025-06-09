(ns nth-prime)

(defn prime? [num]
  (empty? (filter #(zero? (mod num %)) (range 2 num))))


(defn nth-prime 
  "Returns the prime number in the nth position."
  [n]
  (if (zero? n)
    (throw (IllegalArgumentException. "there is no zeroth prime"))
    (loop [count 1 index 2]
      (let [prime-index? (prime? index)]
        (cond (and prime-index? (= count n)) index
              prime-index? (recur (inc count) (inc index))
              :else (recur count (inc index)))))))
