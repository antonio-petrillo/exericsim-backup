(ns accumulate)

(defn accumulate [f coll] ;; <- arglist goes here
  (into [ ] (for [elem coll]
    (f elem))))
