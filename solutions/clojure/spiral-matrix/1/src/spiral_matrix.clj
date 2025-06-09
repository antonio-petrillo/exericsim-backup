(ns spiral-matrix)

(def movements {:up [-1 0]
                :down [1 0]
                :left [0 -1]
                :right [0 1]})

(def next-movements {:right :down
            :down :left
            :left :up
            :up :right})

(defn- valid? [n]
  (fn [[x y]] (and (< -1 x n) (< -1 y n))))

(defn spiral [n]
  (let [matrix (apply vector (repeat n(into [] (repeat n 0))))
        valid? (valid? n)]
    (loop [[h & tl] (range 1 (inc (* n n)))
           matrix matrix
           pos [0 0]
           curr-dir :right]
      (let [next-pos (mapv + pos (curr-dir movements))
            alter-pos (mapv + pos ((next-movements curr-dir) movements))
            valid-next? (and (valid? next-pos) (zero? (get-in matrix next-pos)))]
        (if (nil? h)
          matrix
          (recur
           tl
           (assoc-in matrix pos h)
           (if valid-next? next-pos alter-pos)
           (if valid-next? curr-dir (next-movements curr-dir))))))))
