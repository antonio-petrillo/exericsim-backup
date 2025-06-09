(ns atbash-cipher
  (:require [clojure.string :as s]))


(defn encode [plaintext]
  (let [cipher (zipmap
                (map (partial char) (range 97 123))
                (map (partial char) (range 122 96 -1)))
        plaintext (s/replace plaintext #"[^a-zA-Z0-9]" "")
        encrypted (apply str (map #(get cipher (Character/toLowerCase %) %) plaintext))
        splitted (map #(apply str %) (partition 5 5 "" encrypted))]
    (s/join " " splitted)))
