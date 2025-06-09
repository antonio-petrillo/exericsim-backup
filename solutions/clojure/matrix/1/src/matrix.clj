(ns matrix
  (:require [clojure.string :as s]))

(defn str-vector->int-vector [xs]
  (->> (s/split xs #"\s+")
       (mapv parse-long)))

(defn get-row
  "Returns the i-th row of the matrix s"
  [s i]
  (-> s
      s/split-lines
      (nth (dec i))
      (str-vector->int-vector)))

(defn get-column
  "Returns the i-th column of the matrix s"
  [s i]
  (nth (->> (s/split-lines s)
            (mapv str-vector->int-vector)
            (apply mapv vector))
       (dec i)))
