(ns resistor-color-duo)

(def colors-map
  { "black" 0
    "brown" 1
    "red" 2
    "orange" 3
    "yellow" 4
    "green" 5
    "blue" 6
    "violet" 7
    "grey" 8
    "white" 9 })

(defn resistor-value
  "Returns the resistor value based on the given colors"
  [colors]
  (->> colors
       (take 2)
       (map colors-map)
       (apply str)
       parse-long))
