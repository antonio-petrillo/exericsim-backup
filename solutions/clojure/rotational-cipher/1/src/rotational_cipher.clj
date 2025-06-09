(ns rotational-cipher)

(def alphabet
  (map #(char %) (range 97 123)))

(defn encode [cipher c]
  "Take a single character and encode with the given cipher"
  (let [encoded (cipher (Character/toLowerCase c))]
    (cond (Character/isUpperCase c) (Character/toUpperCase encoded)
          (Character/isLowerCase c) encoded
          :else c)))

(defn rotate [s k] ;; <- arglist goes here
      ;; your code goes here
      (let [k (if (< k 0) (+ 26 (mod k 26)) (mod k 26))
            cipher (zipmap alphabet (take 26 (drop k (cycle alphabet))))
            encode (partial encode cipher)]
        (apply str (map encode s))))
