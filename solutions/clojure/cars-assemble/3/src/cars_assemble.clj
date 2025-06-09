(ns cars-assemble)

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (let [car-per-hour 221
        car-produced (double (* speed car-per-hour))]
    (cond (= speed 0) car-produced
          (and (<= speed 4) (> speed 0)) car-produced
          (and (<= speed 8) (>= speed 5)) (* car-produced 0.9)
          (= speed 9) (* car-produced 0.8)
          (= speed 10) (* car-produced 0.77))))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (/ (production-rate speed) 60)))
