(ns darts)

(defn score
  "Calculates the score of a dart throw"
  [x y]
  (let [r (Math/sqrt (+ (* x x) (* y y)))]
    (cond
      (<= r 1.0) 10
      (<= r 5.0) 5
      (<= r 10.0) 1
      :else 0)))
