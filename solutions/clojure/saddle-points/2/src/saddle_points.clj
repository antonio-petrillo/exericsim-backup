(ns saddle-points)

(defn get-column [m c]
  (into [] (for [i (range (count m))]
            (get-in m [i c]))))

(defn saddle? [m [r c]]
  (let [spot (get-in m [r c])]
    (and (= spot (apply max (get m r)))
         (= spot (apply min (get-column m c))))))

(defn saddle-points
  "Returns the saddle points of a matrix"
  [matrix]
  (if (empty? matrix)
    #{}
    (set (for [r (range (count matrix)) c (range (count (first matrix)))
               :when (saddle? matrix [r c])]
           [(inc r) (inc c)]))))
