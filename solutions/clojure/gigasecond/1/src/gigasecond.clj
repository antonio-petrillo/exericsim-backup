(ns gigasecond)


(defn from [year month day]
 (let [date (java.time.LocalDate/of year month day)
       days-in-gigaseconds (quot 1000000000 (* 3600 24))
       final-date (.plusDays date days-in-gigaseconds)]
   ((juxt (memfn getYear) (memfn getMonthValue) (memfn getDayOfMonth)) final-date)))
