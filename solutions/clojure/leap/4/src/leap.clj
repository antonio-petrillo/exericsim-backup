(ns leap)

(defn leap-year? [year] ;; <- argslist goes here
  (if (zero? (mod year 100))
    (zero? (mod year 400))
    (zero? (mod year 4))))
