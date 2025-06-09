(ns word-count
  (:require [clojure.string :as s]))

(defn word-count [s]
  (->> s
       (re-seq #"\w+")
       (map #(.toLowerCase %))
       (frequencies)))
