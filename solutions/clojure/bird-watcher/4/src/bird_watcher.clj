(ns bird-watcher)

(def last-week [0 2 5 3 7 8 4])

(def birds-per-day [2 5 0 7 4 1])

(defn today [birds]
  (peek birds))

(defn inc-bird [birds]
  (assoc birds (dec (count birds)) (inc (today birds))))

(defn day-without-birds? [birds]
  (true? (some zero? birds)))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (reduce (fn [acc elem] (if (>= elem 5) (inc acc) acc)) 0 birds))

(defn odd-week? [birds]
 (= [1 0 1 0 1 0 1] birds))
