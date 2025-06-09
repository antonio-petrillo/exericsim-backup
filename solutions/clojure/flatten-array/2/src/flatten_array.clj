(ns flatten-array)

(defn flatten [arr]
  (into [] (mapcat
            #(cond (vector? %) (flatten %)
                   % [%]
                   :else [])
            arr)))
