(ns transpose
  (:require [clojure.string :as s]))

(defn max-len [s]
  (->> s
       (map count)
       (apply max)))

(defn pad [len char s]
  (->>  (concat s (repeat char))
        (take len)
        s/join))

(defn trim [{:keys [max result]} col]
  (let [trimmed (s/trimr col)
        size (count trimmed)]
    (if
        (> size max) {:max size :result (conj result trimmed)}
        {:max max :result (conj result (s/join (take max col)))})))

(defn transpose
  "Given a string, it returns the transposed version"
  [s]
  (let [s (s/split-lines s)
        len (max-len s)]
    (->> s
         (map (partial pad len \space))
         (apply mapv str)
         reverse
         (reduce trim {:max 0 :result '()})
         :result
         (s/join "\n"))))
