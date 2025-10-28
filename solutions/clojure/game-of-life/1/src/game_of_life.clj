(ns game-of-life)

(defn neighbours-alive [cells [r c]]
  (let [rows (count cells) cols (count (cells 0))]
    (for [dr (range -1 2) dc (range -1 2)
          :let [nr (+ r dr) nc (+ c dc)]
          :when (and (not= dr dc 0)
                     (>= nr 0) (>= nc 0) (< nr rows) (< nc cols)
                     (= 1 (get-in cells [nr nc])))]
      (get-in cells [nr nc]))))

(defn next-cell-state [cells pos cell]
  (let [num-alive (->> pos (neighbours-alive cells) count)]
    (cond  
      (= 2 num-alive) cell
      (= 3 num-alive) 1
      :else 0)))

(defn tick
  "Returns the next generation of the cells."
  [cells]
  (if (empty? cells)
    []
    (map-indexed 
     (fn [i row] 
       (map-indexed (fn [j cell] (next-cell-state cells [i j] cell)) row)) cells)))