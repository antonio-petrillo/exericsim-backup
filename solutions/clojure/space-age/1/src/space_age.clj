(ns space-age)

(def earth-seconds-per-year 31557600.0)

(defn on-mercury [seconds]
  (/ seconds (* earth-seconds-per-year 0.2408467)))

(defn on-venus [seconds]
  (/ seconds (* earth-seconds-per-year 0.61519726)))

(defn on-earth [seconds]
 (/ seconds earth-seconds-per-year))

(defn on-mars [seconds]
  (/ seconds (* earth-seconds-per-year 1.8808158)))

(defn on-jupiter [seconds]
  (/ seconds (* earth-seconds-per-year 11.862615)))

(defn on-saturn [seconds]
  (/ seconds (* earth-seconds-per-year 29.447498)))

(defn on-uranus [seconds]
  (/ seconds (* earth-seconds-per-year 84.016846)))

(defn on-neptune [seconds]
  (/ seconds (* earth-seconds-per-year 164.79132)))
