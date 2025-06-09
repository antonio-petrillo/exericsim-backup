(ns etl)

(defn transform [source]
  (into {}
        (for [[k l] source]
          (into {} (for [e l] [(.toLowerCase e) k])))))
