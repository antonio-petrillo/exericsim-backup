(ns pig-latin
  (:require [clojure.string :as s]))

(defn word->pig-latin [word]
  (if (re-find #"^[aeiou]|^xr|^yt" word)
    (str word "ay")
    (let [[_ match end] (re-find #"^([^aeiou]?qu|[^aeiouy]+|[^aeiou]+)(.*$)" word)]
      (str end match "ay"))))

(defn translate [sentence]
  (s/join " " (map word->pig-latin (s/split sentence #"\s+"))))
