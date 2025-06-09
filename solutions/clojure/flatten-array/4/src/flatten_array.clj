(ns flatten-array)

(defn flatten [arr]
  (mapcat
    #(cond (vector? %) (flatten %)
      (list? %) (flatten %)
      % (list %)
      :else '())
    arr))
