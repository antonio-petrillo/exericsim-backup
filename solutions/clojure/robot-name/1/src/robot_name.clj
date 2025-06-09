(ns robot-name)

(def letters (mapv (partial char) (range 65 91)))

(defn random-name []
  (str
   (nth letters (rand-int 26))
   (nth letters (rand-int 26))
   (rand-int 10)
   (rand-int 10)
   (rand-int 10)))

(defn robot []
  (atom {:name (random-name)}))

(defn robot-name [robot]
  (@robot :name))

(defn reset-name [robot]
  (swap! robot assoc :name (random-name)))
