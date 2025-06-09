(ns series
  (:require [clojure.string :as s]))

(defn slices [string length] ;; <- arglist goes here
  ;; your code goes here
  (if (= 0 length)
    [""]
    (loop [strlen (count string) result [] start 0]
      (if (< strlen length)
        result
        (recur
         (dec strlen)
         (conj result (subs string start (+ start length)))
         (inc start))))))
