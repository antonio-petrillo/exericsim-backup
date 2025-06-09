(ns perfect-numbers)

(defn classify [num]
  ;; {:pre [(pos? num)]} The exercise require IllegalArgumentException
  (if (pos? num)
    (let [factors (filter #(zero? (mod num %)) (range 1 num))
          sum (apply + factors)]
      (cond (= num sum) :perfect
            (> num sum) :deficient
            :else :abundant))
    (throw (IllegalArgumentException. "Negative numbers throw an exception"))))
