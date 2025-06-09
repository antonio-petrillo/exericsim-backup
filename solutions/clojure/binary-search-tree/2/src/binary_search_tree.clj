(ns binary-search-tree)

(defn value [[_ root _]]
  root)

(defn singleton [root]
  [[] root []])

(defn insert [value [left root right :as tree]]
  (cond (empty? tree) (singleton value)
        (> value root) [left root (insert value right)]
        (<= value root) [(insert value left) root right]))
        ;; :else [left root right]))
        ;; the exercise require to insert on the left if the new value is already present

(defn left [[left _ _]]
  left)

(defn right [[_ _ right]]
  right)

(defn to-list [tree]
  (flatten tree))

(defn from-list [[head & tail]]
  (reduce (fn [root value] (insert value root)) (singleton head) tail))
