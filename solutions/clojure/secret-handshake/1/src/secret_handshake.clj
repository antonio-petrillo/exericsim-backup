(ns secret-handshake)

(defn int->binary [num]
  (loop [result [] num num]
    (if (zero? num)
      (if (empty? result)
        [0]
        result)
      (recur (conj result (mod num 2)) (quot num 2)))))

(defn commands [num]
  (let [instruction  (->> num
                     int->binary
                     (map #(if (zero? %2) "" %1) ["wink" "double blink" "close your eyes" "jump" "reverse"])
                     (filter (complement empty?)))
        instruction (if (= "reverse" (last instruction)) (rest (reverse instruction)) instruction)]
    (into [] instruction)))
