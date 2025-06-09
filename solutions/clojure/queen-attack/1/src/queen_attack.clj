(ns queen-attack)

(defn empty-board []
  (into [] (repeat 8 "_ _ _ _ _ _ _ _\n")))

(defn replace-at [s index replacement]
  (str (subs s 0 index) replacement (subs s (inc index))))

(defn board-string [positions]
  (apply str (reduce
   (fn [board key] (let [piece (if (= key :w) "W" "B")
                        [x y] (positions key)]
                    (update board x #(replace-at % (* 2 y) piece))))
   (empty-board)
   (keys positions))))

(defn valid-pos? [[x y]]
  (and
   (<= 0 x 7)
   (<= 0 y 7)))

(defn create-diags [pos]
  (let [up-left (take-while valid-pos? (iterate (fn [[x y]] [(dec x) (inc y)]) pos))
        up-right (take-while valid-pos? (iterate (fn [[x y]] [(dec x) (dec y)]) pos))
        down-left (take-while valid-pos? (iterate (fn [[x y]] [(inc x) (dec y)]) pos))
        down-right (take-while valid-pos? (iterate (fn [[x y]] [(inc x) (inc y)]) pos))]
    (concat up-left up-right down-left down-right)))

(defn diags? [pos1 pos2]
  (some #(= % pos2) (create-diags pos1)))

(defn can-attack [{:keys [w b]}]
  (cond (= (first w) (first b)) true ;; same row
        (= (second w) (second b)) true ;; same column
        (diags? w b) true
        :else false))
