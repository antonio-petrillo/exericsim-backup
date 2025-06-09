(ns square-root
  (:require [clojure.math :as math]))

(defn square-root
  "Calculates a number's square root with Newton-Rhapson"
  ([n max-iteration epsilon tolerance]
     (letfn [(f [x] (- n (* x x))) ;; f(x) = n - x^2 ;; the root of this equation is the square root of n
             (f-prime [x] (- (* 2 x)))] ;; f'(x) = -2x
       (loop [iteration 0
              x0 n]
         (let [y (f x0)
               y-prime (f-prime x0)
               x1 (abs (- x0 (/ y y-prime)))] ;; next step
           (cond
             (= iteration max-iteration) (throw (IllegalStateException. "Cannot compure square root, max iteration reached"))
             (< (abs y-prime) epsilon) (throw (IllegalStateException. "Cannot compure square root, newton method failed"))
             (< (abs (- x1 x0)) tolerance) (int (math/floor x1))
             :else (recur (inc iteration) x1))))))
  ([n] (square-root n 1000 0.0005 0.0005)))
