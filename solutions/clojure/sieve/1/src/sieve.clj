(ns sieve)
;; I don't like my solution, I think it consume to much memory and redo to much calculation.
;; Obviously I could have take another approach, but I have sticked to this one because I find interesting the functions fmap, fmap-key and mark
;; I'm pretty sure that I will find a one liner in community solution 🙂.

(defn unmarked? [m k]
  (= :unmarked (m k)))

(defn fmap [f m]
  (into {} (for [[k v] m] {k (f v)})))

(defn fmap-key [f m]
  (into (sorted-map) (for [[k _] m] {k (f k)})))
(defn mark [m k]
  (fmap-key #(if (and (zero? (mod % k)) (not= % k)) :marked (m %)) m))

(defn sieve [up-to]
  (loop [candidates (into (sorted-map) (zipmap (range 2 (inc up-to)) (repeat :unmarked)))
         indexes (range 2 (inc (quot up-to 2)))]
    (if (empty? indexes)
      (mapv first (filter #(unmarked? candidates (first %)) candidates))
      (let [[head & tail] indexes]
        (if (unmarked? candidates head)
          (recur (mark candidates head) tail)
          (recur candidates tail))))))
