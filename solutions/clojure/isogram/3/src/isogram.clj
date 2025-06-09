(ns isogram)

;; (defn isogram? [word]
;;   (let [letters (filter #(Character/isLetter %) word)]
;;     (= (count letters)
;;        (count (set (map #(Character/toUpperCase %) letters))))))

;; after seeing community solution I realized this could be done with frequencies

(defn isogram? [word]
  (->> word
       (filter #(Character/isLetter %))
       (map #(Character/toLowerCase %))
       (frequencies)
       (every? #(= 1 (second %)))))
