(ns dominoes)

(defn- flip [[x y]] [y x])

(defn- match? [domino-1 domino-2]
  (= (second domino-1) (first domino-2)))

(defn- one? [n] (= 1 n))

(defn- remove-one-dup [v entry]
  (loop [res []
         [h & tl] v]
    (if (= h entry)
      (into [] (concat res tl))
      (recur (conj res h) tl))))

(defn- comb [dominoes]
  (if (one? (count dominoes))
    (distinct [[(first dominoes)] [(flip (first dominoes))]])
    (for [el dominoes cs (comb (remove-one-dup dominoes el))
          :when (or (match? (last cs) el) (match? (last cs) (flip el)))]
      (cond (match? (last cs) el) (conj cs el)
            (match? (last cs) (flip el)) (conj cs (flip el))
            :else ()))))

(defn can-chain? [[domino &_ :as dominoes]]
  (cond (empty? dominoes) true
        (one? (count dominoes)) (= (first domino) (second domino))
        :else (->> dominoes
                   comb
                   (remove empty?)
                   (filter #(match? (last %) (first %)))
                   count
                   (< 0))))
