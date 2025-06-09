(ns isogram)

(defn isogram? [word]
  (let [letters (filter #(Character/isLetter %) word)]
    (= (count letters)
       (count (set (map #(Character/toUpperCase %) letters))))))
