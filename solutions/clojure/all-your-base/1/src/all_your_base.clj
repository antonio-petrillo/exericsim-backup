(ns all-your-base)

(defn input->base10
  [base digits]
  (reduce + 0
          (map * (reverse digits) (iterate #(* base %) 1))))

(defn base10->base-n
  [base-n input]
  (loop [num input result ()]
    (if (zero? num)
      (if (empty? result) '(0) result)
      (recur
       (quot num base-n)
       (conj result (mod num base-n))))))

(defn convert [start-base digits dest-base] ;; <- arglist goes here
  ;; your code goes here
  (let [value (input->base10 start-base digits)]
    (cond (or (<= start-base 1) (<= dest-base 1)) nil
          (some #(or (< % 0) (>= % start-base)) digits) nil
          (empty? digits) nil
          :else (base10->base-n dest-base value))))
