(ns clock)

(defn clock->string [clock]
  (let [hours (first clock)
        minutes (second clock)
        hours-pad (if (> hours 9) "" "0")
        minutes-pad (if (> minutes 9) "" "0")]
    (str hours-pad hours ":" minutes-pad minutes)))

(defn clock [hours minutes]
  (let [minute (mod minutes 60)
        carry (if (neg? minutes) (dec (quot minutes 60)) (quot minutes 60))
        hours (mod (+ hours carry) 24)]
    [hours minute]))

(defn add-time [old-clock time]
  (clock (first old-clock) (+ time (second old-clock))))
