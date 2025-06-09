(ns robot-name)

(def letters (mapv char (range (int \A) (int \Z))))
(def robots (atom #{}))

(defn random-name []
  (let [name (format "%c%c%3d"
                (rand-nth letters)
                (rand-nth letters)
                (rand-int 1000))]
    (if (@robots name)
      (recur)
      (do
        (swap! robots conj name)
        name))))

(defn robot []
  (atom {:name (random-name)}))

(defn robot-name [robot]
  (@robot :name))

(defn reset-name [a-robot]
  (let [old-name (robot-name a-robot)]
    (do
      (swap! a-robot assoc :name (random-name))
      (swap! robots disj old-name))))
