(ns etl
  (:require [clojure.string :as s]))

(defn transform [source]
  (into {} (mapcat (fn [[k l]] (for [e l] [(s/lower-case e) k])) source)))
