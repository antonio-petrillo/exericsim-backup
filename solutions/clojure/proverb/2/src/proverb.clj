(ns proverb
  (:require [clojure.string :as s]))

(defn say [[s1 s2]]
  (str "For want of a " s1 " the " s2 " was lost." ))

(defn recite [strings]
  (if (empty? strings) ""
      (let [last-phrase (str "And all for the want of a " (first strings) ".")
            body-phrases (->> strings
             (partition 2 1)
             (mapv say))]
        (s/join "\n" (conj body-phrases last-phrase)))))
