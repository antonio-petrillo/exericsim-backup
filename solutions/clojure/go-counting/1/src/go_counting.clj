(ns go-counting
  (:require [clojure.set :as s]))

(defn valid-start-pos? [col row cols-size rows-size]
  (let [cols-size (if (zero? cols-size) 1 cols-size)
        rows-size (if (zero? rows-size) 1 rows-size)]
    (cond (or (< col 0) (< row 0)) false
          (or (>= col cols-size) (>= row rows-size)) false
          :else true)))

(def tokens {\W :white
             \B :black
             \space nil})

(defn parse-grid [grid]
  (mapv (fn [row] (mapv #(tokens %) row)) grid))

(def offsets [[0 1] [0 -1] [1 0] [-1 0]])

(defn neighbours [[col row] cols-size rows-size]
  (set (for [[drow dcol] offsets
        :when (valid-start-pos? (+ dcol col) (+ drow row) cols-size rows-size)]
    [(+ dcol col) (+ drow row)])))

(defn neighbours-colors [grid neighbours]
  (into #{} (map #(get-in grid (reverse %)) neighbours)))

(defn free-neighbours [grid neighbours]
  (into #{} (filter #(nil? (get-in grid (reverse %))) neighbours)))

(defn territory [grid [col row]]
  (let [queue (conj clojure.lang.PersistentQueue/EMPTY [col row])
        rows-size (count grid)
        cols-size (count (first grid))
        _ (when-not (valid-start-pos? col row cols-size rows-size) (throw (IndexOutOfBoundsException.)))
        grid (parse-grid grid)]
    (if (get-in grid [row col])
      {:stones #{} :owner nil}
    (loop [queue queue visited #{} stones #{} colors #{}]
      (if (empty? queue)
        {:stones stones
         :owner (if (or (zero? (count stones))
                     (->> colors
                          (remove nil?)
                          (count)
                          (< 1)))
                  nil
                  (first (remove nil? colors)))}
        (let [coordinate (peek queue)
              neighbours (s/difference (neighbours coordinate cols-size rows-size) visited)
              neighbours-colors (neighbours-colors grid neighbours)
              empty-neighbours (free-neighbours grid neighbours)]
          (recur
           (if (empty? empty-neighbours)
             (pop queue)
             (reduce conj (pop queue) empty-neighbours))
           (if (empty? neighbours)
             (conj visited coordinate)
             (->> (conj visited coordinate)
                  (into neighbours)))
           (conj stones (peek queue))
           (into colors neighbours-colors))))))))

(def owner->territory
  {:black :black-territory
   :white :white-territory
   nil :null-territory})

(defn territories [grid]
  (let [coordinates
        (into #{}
              (for [col (range (count (first grid))) row (range (count grid))]
                [col row]))]
    (loop [coordinates coordinates
           result {:black-territory #{}
                   :white-territory #{}
                   :null-territory #{}}]
      (if (empty? coordinates)
        result
        (let [coordinate (first coordinates)
              next-territory (territory grid coordinate)
              remaining (disj coordinates coordinate)]
          (if (empty? (:stones next-territory))
            (recur remaining result)
            (recur (s/difference remaining (:stones next-territory))
                   (update result (owner->territory (:owner next-territory))
                           #(into % (:stones next-territory))
                           ))))))))
