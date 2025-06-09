(ns eliuds-eggs)

(defn egg-count [number]
    (loop [n number c 0]
      (if (zero? n)
        c 
        (recur (bit-and n (dec n)) (inc c)))))