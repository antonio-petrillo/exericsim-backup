(ns wordy)

(defn evaluate [s] ;; <- arglist goes here
      ;; your code goes here
  (let [s (clojure.string/trim s)
        values (map read-string (re-seq #"-?\d+" s))
        operations (apply vector (re-seq #"plus|minus|multiplied|divided|raised|cubed" s))]
    (cond (not= (count values) (inc (count operations))) (throw ( IllegalArgumentException. "Invalid form" ))
          (= 0 (count values)) (throw ( IllegalArgumentException. "A non math question" ))
          (some #(= "cubed" %) operations) (throw ( IllegalArgumentException. "A non math question" ))
          :else (second (reduce (fn [acc elem]
                            (let [index (first acc) value (second acc) operation-type (get operations index)]
                              (vector (inc index) (
                                                   (cond (= operation-type "plus") +
                                                         (= operation-type "minus") -
                                                         (= operation-type "multiplied") *
                                                         (= operation-type "divided") /
                                                         (= operation-type "raise") nil)
                                                   value elem))
                              ))
                          [0 (first values)] (rest values))))))
