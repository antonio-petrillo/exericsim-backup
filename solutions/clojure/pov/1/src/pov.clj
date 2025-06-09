(ns pov)

(defn remove-at [vector index]
  (into (subvec vector 0 index) (subvec vector (inc index))))

(defn of-helper [target tree]
  (cond (= target (first tree)) (list tree)
        (not (nil? tree))
        (->> tree
             (map #(vector %1 (if (vector? %2) (of-helper target %2))) (range))
             (filter (fn [[_ t]] (not (empty? t))))
             first
             ((fn [[index res]]
               (if-not (nil? res)
                 (conj res (remove-at tree index))))))
        :else nil))

(defn of [target tree]
  (if-let [h (of-helper target tree)]
    (reduce (fn [acc elem] (conj elem acc)) h)))

(defn gen-path [tree to]
  (cond (or (not (vector? tree))
            (nil? (first tree))) nil
        (= (first tree) to) (list to)
        :else (->> tree
                   rest
                   (map #(gen-path % to))
                   (remove #(or (empty? %) (nil? %)))
                   first
                   (#(when % (conj % (first tree)))))))

(defn path-from-to [from to tree]
  (if-let [tree (of from tree)]
    (gen-path tree to)))
