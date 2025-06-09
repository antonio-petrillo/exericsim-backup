(ns list-ops)

(defn append 
  "Given two vectors, it adds all the items in the second vector to the end of the first vector"
  [coll1 coll2]
  (reduce conj coll1 coll2))

(defn concatenate 
  "Given a vector of vectors, it combines all the vectors into one flattened vector"
  [colls]
  (reduce append [] colls))

(defn select-if
  "Given a predicate and a vector, it returns the vector of all items for which predicate(item) is true"
  [pred coll]
  (reduce
   (fn [acc el] (if (pred el) (conj acc el) acc))
   []
   coll))

(defn length 
  "Given a vector, it returns the number of items within it"
  [coll]
  (reduce (fn [acc _] (inc acc)) 0 coll))

(defn apply-to-each 
  "Given a function and a vector, it returns the vector of the results of applying function(item) on all items"
  [f coll]
  (reduce (fn [acc el] (conj acc (f el))) [] coll))

(defn foldl 
  "Given a function, a vector, and initial accumulator, it folds (reduces) each item into the accumulator from the left"
  [f coll acc]
  (reduce f acc coll))

(defn reverse-order 
  "Given a vector, it returns a vector with all the original items, but in reverse order"
  [coll]
  (->> coll 
    (reduce conj '())
    (into [])))

(defn foldr
  "Given a function, a vector, and an initial accumulator, it folds (reduces) each item into the accumulator from the right"
  [f coll acc]
  (->> coll
    reverse-order
    (reduce f acc)))
