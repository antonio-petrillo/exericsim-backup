(ns anagram)

(defn anagram? [w1 w2]
    (let [w1 (.toLowerCase w1)
          w2 (.toLowerCase w2)]
    (and (not= w1 w2)
         (= (sort (.toLowerCase w1))
            (sort (.toLowerCase w2))))))

(defn anagrams-for [word anagrams]
    (filter (partial anagram? word) anagrams))
