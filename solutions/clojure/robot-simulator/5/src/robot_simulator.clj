(ns robot-simulator)

(defn robot [coordinates bearing] ;; <- arglist goes here
  {:coordinates coordinates, :bearing bearing})

(defn advance [{:keys [coordinates bearing]}]
  (let [xpos (:x coordinates) ypos (:y coordinates)]
    (cond (= bearing :north) (robot {:x xpos :y (inc ypos)} bearing)
          (= bearing :south) (robot {:x xpos :y (dec ypos)} bearing)
          (= bearing :east) (robot {:x (inc xpos) :y ypos} bearing)
          (= bearing :west) (robot {:x (dec xpos) :y ypos} bearing))))

(defn turn-right [direction] ;; <- arglist goes here
  ;; your code goes here
  (get {:north :east, :east :south, :south :west, :west :north} direction))

(defn turn-left [direction] ;; <- arglist goes here
  ;; your code goes here
  (get {:north :west, :west :south, :south :east, :east :north} direction))

;; AHHHHHHHHHH, I've inverted instructions and robot-start, this taken me 30 minutes!
(defn simulate [instructions robot-start] ;; <- arglist goes here
  (reduce (fn [{:keys [coordinates bearing] :as robot-state } instruction]
            (cond (= \R instruction) (robot coordinates (turn-right bearing))
                  (= \L instruction) (robot coordinates (turn-left bearing))
                  (= \A instruction) (advance robot-state)))
          robot-start
          (seq instructions)))
