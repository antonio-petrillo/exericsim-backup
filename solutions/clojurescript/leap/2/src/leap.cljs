(ns leap)

(defn leap-year? [year]
 (and
  (zero? (mod year 4))
  (or ((complement zero?) (mod year 100))
      (zero? (mod year 400)))))
