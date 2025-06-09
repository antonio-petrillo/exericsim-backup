(ns simple-cipher
  (:require [clojure.string :as s]))

(def ^:private lowercase-offset (int \a))

(defn rand-key
  "Returns a random key"
  []
  (->> (for [_ (range 100)]
         (rand-int 26))
       (map #(char (+ 97 %)))
       (apply str)))

(defn shift [op key letter]
  (let [num-1 (- (int key) lowercase-offset)
        num-2 (- (int letter) lowercase-offset)]
    (char (+ lowercase-offset (mod (op num-1 num-2) 26)))))

(defn encode
  "Encodes text using the specified key"
  ([op key plaintext]
   (->> (cycle key)
        (map (partial shift op) plaintext)
        s/join))
  ([key plaintext]
   (encode + key plaintext)))

(defn decode
  "Decodes text using the specified key"
  [key ciphertext]
  (encode - key ciphertext))
