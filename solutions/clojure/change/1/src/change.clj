(ns change)

(defn build-lookup-table [up-to set]
  (let [sorted (sort set)]
  (loop [index 1 table {0 []}]
    (if (= index (inc up-to))
      table
      (->> (map #(vector % (- index %)) sorted)
           (filter (fn [[_ s]] (>= s 0)))
           (map #(conj (get table (second %)) (first %)))
           (filter vector?) ;; otherwise sometimes when a change can be achieved a list end up in the table
           (sort-by count)
           first
           (assoc table index)
           (recur (inc index)))))))

(defn issue [to-change coins]
  (cond (neg? to-change) (throw (IllegalArgumentException. "cannot change"))
        :else (let [table (build-lookup-table to-change coins)
                     result (table to-change)]
                 (if result
                   (sort result)
                   (throw (IllegalArgumentException. "cannot change"))))))
