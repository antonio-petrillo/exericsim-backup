(ns minesweeper
  (:require [clojure.string :as s]))

(defn make-position-validator [rows-num columns-num]
  (fn [x y]
    (and (< -1 x rows-num)
         (< -1 y columns-num))))

(defn neighbors [[x y] valid-f]
  (for [[update-x update-y] [[inc inc] [dec inc]
                             [dec dec] [inc dec]
                             [inc identity] [dec identity]
                             [identity inc] [identity dec]]
        :let [neighbor-x (update-x x)
              neighbor-y (update-y y)]
        :when (valid-f neighbor-x neighbor-y)]
    [neighbor-x neighbor-y]))

(defn string->board [s]
  (->> s
       (s/split-lines)
       (mapv #(s/split % #""))))

(defn bomb? [cell] (= "*" cell))

(defn draw-cell? [cell value]
  (and (= " " cell)
       (pos? value)))

(defn draw [input]
  (let [board (string->board input)
        rows (count board)
        columns (count (nth board 0))
        final-board (for [x (range 0 rows) y (range 0 columns)
                          :let [valid? (make-position-validator rows columns)
                                neighbors (neighbors [x y] valid?)
                                cell (get-in board [x y])
                                value (count (filter bomb? (map #(get-in board %) neighbors)))]]
                      (if (draw-cell? cell value) value cell))]
    (s/join "\n" (map (partial apply str) (partition columns final-board)))))
