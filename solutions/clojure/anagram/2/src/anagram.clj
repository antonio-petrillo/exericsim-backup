(ns anagram)

(defn anagram? [s1 s2]
  (let [lower1 (.toLowerCase s1) lower2 (.toLowerCase s2)]
  (and
   (not= lower1 lower2)
   (= (sort lower1) (sort lower2)))))

(defn anagrams-for [word prospect-list]
  (filter (partial anagram? word) prospect-list))
