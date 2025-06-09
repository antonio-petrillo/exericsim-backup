(ns acronym
  (:require [clojure.string :as s]))

(defn get-uppers [s]
  (let [res (filter #(Character/isUpperCase %) s)
        len (count res)
        num-chars (count (filter #(Character/isLetter %) s))]
    (cond (= num-chars len) (first res)
          (> len 1) res
          :else (-> (first s) Character/toUpperCase))))

(defn acronym
  "Converts phrase to its acronym."
  [phrase]
  (apply str (flatten (map #(get-uppers %) (s/split phrase #"\s|-")))))
