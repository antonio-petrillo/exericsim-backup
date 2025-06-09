(ns binary
  (:require [clojure.string :as s]))

(defn to-decimal [s] ;; <- arglist goes here
  ;; your code goes here
  (let [digits  (->> s
                     (re-seq #"[01]")
                     (map #(Integer/parseInt %))
                     (reverse))]
    (reduce + 0 (map * digits (iterate #(* 2 %) 1)))))
