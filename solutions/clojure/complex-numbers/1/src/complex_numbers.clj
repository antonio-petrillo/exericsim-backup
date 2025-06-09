(ns complex-numbers)

(defn real [[a b]] ;; <- arglist goes here
  ;; your code goes here
  a)

(defn imaginary [[a b]] ;; <- arglist goes here
  ;; your code goes here
  b)

(defn abs [[a b]] ;; <- arglist goes here
  ;; your code goes here
  (Math/sqrt (+ (* a a) (* b b))))

(defn conjugate [[a b]] ;; <- arglist goes here
  ;; your code goes here
  [a (- b)])

(defn add [[a b] [c d]] ;; <- arglist goes here
  ;; your code goes here
  [(+ a c) (+ b d)])

(defn sub [[a b] [c d]] ;; <- arglist goes here
  ;; your code goes here
  [(- a c) (- b d)])

(defn mul [[a b] [c d]] ;; <- arglist goes here
  ;; your code goes here
  [(- (* a c) (* b d)) (+ (* b c) (* a d))])

(defn div [[a b] [c d]] ;; <- arglist goes here
  ;; your code goes here
  (let [c-plus-d-square (+ (* c c) (* d d))]
    [(/ (double (+ (* a c) (* b d))) c-plus-d-square)
     (/ (double (- (* b c) (* a d))) c-plus-d-square)]))
