(ns run-length-encoding
  (:require [clojure.string :as s]))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (loop [result "" char-seq (seq plain-text)]
    (if (empty? char-seq)
      result
      (let [char (first char-seq)
            chars (take-while #(= % char) char-seq)
            num (count chars)
            char-seq (drop num char-seq)
            num (if (= 1 num) "" num)]
        (recur (str result num char ) char-seq)))))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (let [parsed (re-seq #"(\d*)([a-zA-Z]|\s)" cipher-text)]
    (reduce (fn [result [_ num char]]
              (let [num (if (empty? num) 1 (Integer/parseInt num))]
                (apply str result (repeat num char))))
            ""
            parsed)))
