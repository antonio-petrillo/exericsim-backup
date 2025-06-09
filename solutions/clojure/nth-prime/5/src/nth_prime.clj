(ns nth-prime)

;; too slow
;; (defn prime? [num]
;;   (empty? (filter #(zero? (mod num %)) (range 2 num)))))

;; too slow
;; (defn prime? [num]
;;   (empty? (filter #(zero? (mod num %)) (range 2 (inc (quot num 2))))))

;; too slow
;; (defn prime? [num]
;;   (loop [d 2]
;;     (cond (= num d) true
;;           (zero? (mod num d)) false
;;           :else (recur (inc d)))))

;; to slow
;; (defn prime? [num]
;;   (let [limit (inc (quot num 2))]
;;     (loop [d 2]
;;       (cond (>= d limit) true
;;             (zero? (mod num d)) false
;;             :else (recur (inc d))))))

(defn prime? [num]
  (loop [d 2]
    (cond (> (* d d) num) true
          (zero? (mod num d)) false
          :else (recur (inc d)))))

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
