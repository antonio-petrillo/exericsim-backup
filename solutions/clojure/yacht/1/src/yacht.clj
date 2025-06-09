(ns yacht)

(defn nums? [num]
  (fn [dices]
    (let [freqs (get (frequencies dices) num)]
      (if freqs
        (* freqs num)
        0))))

(defn full-house? [dices]
  (if (->> (frequencies dices)
           (map second)
           (sort)
           (= '(2 3)))
    (apply + dices)
    0))

(defn four-of-a-kind? [dices]
  (let [frequencies (frequencies dices)
        values (map second frequencies)
        keys (map first frequencies)]
    (cond (= '(5)   values) (* 4 (first keys))
          (= '(4 1) values) (* 4 (first keys))
          (= '(1 4) values) (* 4 (second keys))
          :else 0)))

(defn little-straight? [dices]
  (if (->> dices
           sort
           (= '(1 2 3 4 5)))
    30
    0))

(defn big-straight? [dices]
  (if (->> dices
           sort
           (= '(2 3 4 5 6)))
    30
    0))

(defn choice [dices]
  (apply + dices))

(defn yacht? [dices]
  (if (->> dices
           frequencies
           (map second)
           (= '(5)))
    50
    0))

(defn score [dices move]
  (cond (= "ones" move) ((nums? 1) dices)
        (= "twos" move) ((nums? 2) dices)
        (= "threes" move) ((nums? 3) dices)
        (= "fours" move) ((nums? 4) dices)
        (= "fives" move) ((nums? 5) dices)
        (= "sixes" move) ((nums? 6) dices)
        (= "full house" move) (full-house? dices)
        (= "four of a kind" move) (four-of-a-kind? dices)
        (= "little straight" move) (little-straight? dices)
        (= "big straight" move) (big-straight? dices)
        (= "choice" move) (choice dices)
        (= "yacht" move) (yacht? dices)))
