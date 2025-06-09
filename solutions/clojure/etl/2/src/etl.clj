(ns etl)

(defn transform [source]
  (into {} (mapcat (fn [[k l]] (for [e l] [(.toLowerCase e) k])) source)))
