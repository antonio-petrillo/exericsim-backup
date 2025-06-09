(ns trinary)

(defn to-decimal [trinary]
  (if (re-find #"[^012]" trinary)
    0
    (let [digits (reverse (map #(Integer/parseInt (str %)) trinary))
          power-of-3 (iterate (partial * 3) 1)]
      (apply + (map * digits power-of-3)))))
