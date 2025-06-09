(ns phone-number
  (:require [clojure.string :as s]))

;; inspired by [Erik Schierboom](https://exercism.org/tracks/clojure/exercises/phone-number/solutions/ErikSchierboom) solution
(defn number [num-string]
  (let [digits (->> (s/replace num-string #"[^\d]" "")
                    (drop-while #(= \1 %))
                    (apply str))
        valid? (re-matches #"[2-9]\d{2}[2-9]\d{6}" digits)]
    (if valid?
      digits
      "0000000000")))

(defn area-code [num-string]
  (apply str (take 3 (number num-string))))

(defn pretty-print [num-string]
  (let [number (number num-string)
        area (subs number 0 3)
        triplet (subs number 3 6)
        rest (subs number 6)]
    (str "(" area ") " triplet "-" rest)))
