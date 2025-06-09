(ns high-scores)

(def scores
  "Returns all scores"
  identity)

(def latest
  "Returns the latest score"
  last)

(def personal-best
  "Returns the top (highest) score"
  (partial apply max))

(defn personal-top-three
  "Returns the top three scores"
  [scores]
  (->> scores (sort >) (take 3)))
