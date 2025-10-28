(ns game-of-life)

(defn count-neighbours-alive [cells [r c]]
  (let [rows (count cells) cols (count (cells 0))]
    (->> (for [dr (range -1 2) dc (range -1 2)
            :let [nr (+ r dr) nc (+ c dc)]
            :when (and (not= dr dc 0))]
        (get-in cells [nr nc] 0))
    (reduce + 0))))

(defn next-gen [cells pos cell]
  (let [num-alive (count-neighbours-alive cells pos)]
    (cond  
      (= 2 num-alive) cell
      (= 3 num-alive) 1
      :else 0)))

(defn tick
  "Returns the next generation of the cells."
  [cells]
  (if (empty? cells)
    []
    (mapv (fn [i row] 
            (mapv #(next-gen cells [i %1] %2) (range) row))
          (range) cells)))