(ns strain)

(defn retain [p c] ;; <- arglist goes here
  ;; your code goes here
  (loop [c c result []]
    (if (empty? c)
      result
      (let [head (first c) tail (rest c)]
        (recur
         tail
         (if (p head)
           (conj result head)
           result))))))

(defn discard [p c] ;; <- arglist goes here
  ;; your code goes here
  (retain (complement p) c))
