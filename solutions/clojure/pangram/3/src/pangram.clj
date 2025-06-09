(ns pangram)

(defn pangram? [s]
  (-> (.toLowerCase s)
      (clojure.string/replace #"[^a-z]" "")
      set
      count
      (= 26)))
