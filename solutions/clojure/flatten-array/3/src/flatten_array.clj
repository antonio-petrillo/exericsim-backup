(ns flatten-array)

(defn flatten [arr]
  (into [] (mapcat
            #(cond (vector? %) (flatten %)
                   (list? %) (flatten %)
                   % (list %)
                   :else '())
            arr)))
