(ns raindrops)

(def default-entries ['(3 "Pling") '(5 "Plang") '(7 "Plong")])

(defn convert ([input entries] 
  (let [res (->> entries
                 (filter #(zero? (mod input (first %))))
                 (map second))]
  (if (empty? res)
    (str input)
    (apply str res))))
([input] (convert input default-entries)))
  


