(ns card-games)

(defn rounds
  "Takes the current round number and returns 
   a `list` with that round and the _next two_."
  [n]
  (list n (inc n) (+ 2 n)))

(defn concat-rounds 
  "Takes two lists and returns a single `list` 
   consisting of all the rounds in the first `list`, 
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))

(defn contains-round? 
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (if (some #{n} l)
    true
    false))

(defn card-average
  "Returns the average value of a hand"
  [hand]
  (double (/ (apply + hand) (count hand))))

(card-average '(5 6 7))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [avg_12 (double (/ (+ (first hand) (last hand)) 2))
        avg (card-average hand)
        middle (double (nth hand (/ (count hand) 2)))]
    (or (= avg avg_12)
        (= avg middle))))

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes 
   is the same as the average of the cards at odd indexes."
  [hand]
  (loop [i 0
         even '()
         odd '()
         [h & tl] hand]
    (if (nil? h)
      (= (card-average even) (card-average odd))
      (if (even? i)
        (recur (inc i) (conj even h) odd tl)
        (recur (inc i) even (conj odd h) tl)))))


(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (let [rev (reverse hand)]
    (if (not= 11 (first rev))
      hand
      (into '(22) (rest rev)))))
