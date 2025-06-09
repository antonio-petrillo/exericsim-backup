(ns meetup
  (:import (java.time YearMonth DayOfWeek)))

(defn leap? [year]
  (and (zero? (mod year 4))
       (or (not= 0 (mod year 100))
           (zero? (mod year 400)))))

(def weekdays [:monday :tuesday :wednesday :thursday :friday :saturday :sunday])

(defn- month->map [year month]
  (let [local-date (YearMonth/of year month)
        month (.getMonth local-date)
        days (.length month (leap? year))
        start-with (.ordinal (.getDayOfWeek (.atDay local-date 1)))]
    (reduce (fn [acc [day weekday]]
              (update acc weekday #(conj % day)))
            (zipmap weekdays (repeat (sorted-set)))
            (zipmap (range 1 (inc days)) (drop start-with (cycle weekdays))))))

(def clauses {
               :first #(first %)
               :second #(second %)
               :third #(first (drop 2 %))
               :fourth #(first (drop 3 %))
               :teenth (fn [s] (first (drop-while #(< % 13) s)))
               :last #(last %)})

(defn get-day-by-clause [month weekday clause]
  ((clause clauses) (month weekday)))

(defn meetup [month year weekday clause]
  (let [day (get-day-by-clause (month->map year month) weekday clause)]
    [year month day]))
