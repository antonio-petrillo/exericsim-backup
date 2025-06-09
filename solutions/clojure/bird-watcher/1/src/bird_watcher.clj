(ns bird-watcher)

(def last-week [0 2 5 3 7 8 4])

(def birds-per-day [2 5 0 7 4 1])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (assoc birds (dec (count birds)) (inc (today birds))))

(defn day-without-birds? [birds]
  (if (some #(= 0 %) birds) true false))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (reduce (fn [acc elem] (if (>= elem 5) (inc acc) acc)) 0 birds))

(defn odd-week? [birds]
  (first
   (reduce
    (fn [acc elem]
      (let [truth (first acc) value (second acc)]
        (if truth
          (cond (= value 0) [(if (= elem 1) true false) elem]
                (= value 1) [(if (= elem 0) true false) elem]
                :else [false elem])
          [false value])))
    [true (first birds)] (rest birds))))
