(ns pascals-triangle)

(def triangle
  (iterate (fn [row]
             (let [prev1 (conj row (bigint 0))
                   prev2 (into [(bigint 0)] row)]
               (mapv + prev1 prev2)))
           [(bigint 1)]))

(defn row [n]
  (last (take n triangle)))
