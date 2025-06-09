(ns flatten-array)

(defn flatten [arr] ;; <- arglist goes here
  ;; your code goes here
  (into [] (apply concat (map
                          #(cond (vector? %) (flatten %)
                                 % [%]
                                 :else [])
                              arr))))
