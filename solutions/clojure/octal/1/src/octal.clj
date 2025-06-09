(ns octal)

(defn to-decimal [octal]
 (if (re-find #"[^0-7]" octal)
   0
   (let [digits (reverse (map #(Integer/parseInt (str %)) (seq octal)))]
     (apply + (map * digits (iterate #(* % 8) 1))))))
