(ns leap)

(defn leap-year? [year] ;; <- argslist goes here
  (if (= 0 (mod year 4))
    (if-not (= 0 (mod year 100))
      true
      (if (= 0 (mod year 400))
        true))))
