(ns crypto-square
  (:require [clojure.string :as s]))

(defn normalize-plaintext [plaintext]
  (.toLowerCase (s/replace plaintext #"[^a-zA-Z0-9]" "")))


(defn square-size [normalized]
  (let [len (count normalized)
        candidates (for [c (range len) r (range len)
                         :when (or (= c r) (= c (inc r)))]
                     [c r])]
    ((comp first first)
     (drop-while #(< (* (first %) (second %)) len) candidates))))

(defn- segments [plaintext]
  (let [normalized (normalize-plaintext plaintext)
        size (square-size normalized)]
    (map (partial apply str) (partition size size (repeat " ") normalized))))

;; I hate the test on this function, it forced me to introduce another function.
;; Furthormore the segments contains the space!
;; Why the heck the test require the segments trimmed?
;; Later I can't use this function because the result is trimmed.
(defn plaintext-segments [plaintext]
  (->> plaintext
       segments
       (mapv s/trim)))

(defn ciphertext [plaintext]
  (->> plaintext
       segments
       (apply map str)
       (map s/trim)
       (s/join "")))

(defn normalize-ciphertext [plaintext]
  (->> plaintext
       segments
       (apply map str)
       (s/join " ")))
