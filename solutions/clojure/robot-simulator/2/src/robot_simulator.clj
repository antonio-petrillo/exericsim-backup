(ns robot-simulator)

(defn robot [coordinates bearing] ;; <- arglist goes here
  {:coordinates coordinates, :bearing bearing})

(defn advance [{:keys [coordinates bearing]}]
  (let [xpos (:x coordinates) ypos (:y coordinates)]
    (cond (= bearing :north) (robot {:x xpos :y (inc ypos)} bearing))
    (= bearing :south) (robot {:x xpos :y (dec ypos)} bearing)
    (= bearing :est) (robot {:x (inc xpos) :y ypos} bearing)
    (= bearing :west) (robot {:x (dec xpos) :y ypos} bearing)))

(defn turn-right [direction] ;; <- arglist goes here
  ;; your code goes here
  (direction {:north :est, :est :south, :south :west, :west :north}))

(defn turn-left [direction] ;; <- arglist goes here
  ;; your code goes here
  (direction {:north :west, :west :south, :south :est, :est :north}))

(defn simulate [instructions robot-start] ;; <- arglist goes here
  (reduce (fn [robot-state instruction]
            (cond (= \R) (robot (:coordinates robot-state) (turn-right (:bearing robot-state)))
                  (= \L) (robot (:coordinates robot-state) (turn-left (:bearing robot-state)))
                  :else (advance robot) ;; (= \A) instruction
                  ))
          robot-start
          instructions))
