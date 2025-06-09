(ns isbn-verifier
  (:require [clojure.string :as s]))

;; (defn isbn? [isbn] ;; <- arglist goes here
;;   (if (every? #(or (Character/isDigit %) (= % \X) (= % \-)) isbn)
;;     (= 0
;;        (mod
;;         (reduce + 0
;;          (map *
;;               (range 10 1 -1)
;;               (map
;;                #(cond (= % "X") 10
;;                       :else (Integer/parseInt %))
;;                (re-seq #"\d|X" isbn))))
;;         11))
;;     false))

(defn isbn? [isbn]
  (let [digits (re-seq #"\d|X" isbn)
        numbers (map #(if (= % "X") 10 (Integer/parseInt %)) digits)
        check-digit-count (count (filter #(= "X" %) digits))]
    (cond (not (every? #(or (Character/isDigit %) (= % \X) (= % \-)) isbn)) false
          (and (not= "X" (last digits)) (> check-digit-count 0)) false
          (not= 10 (count digits)) false
          (> check-digit-count 1) false
          :else (= 0 (mod (reduce + 0 (map * numbers (range 10 0 -1))) 11)))))
