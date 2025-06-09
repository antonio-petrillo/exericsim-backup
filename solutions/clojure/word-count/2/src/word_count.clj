(ns word-count
  (:require [clojure.string :as str]))

(def ^:private punctuation #{\. \: \' \, \! \? \& \@ \^ \$ \% \*})

(defn clean [s]
  (->> s
       (drop-while #(punctuation %))
       reverse
       (drop-while #(punctuation %))
       reverse
       (apply str)))

(defn word-count [s]
  (->>
   (str/split s #"[\s,.]")
   (map clean)
   (remove empty?)
   (map #(.toLowerCase %))
   (frequencies)))
