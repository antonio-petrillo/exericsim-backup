(ns binary-search)

(defn middle
  ([elements] ;; <- arglist goes here
  ;; your code goes here
   (middle 0 (dec (count elements))))
  ([low high] (quot (+ low high) 2)))

(defn search-for [to-find elements]
  (loop [low 0
         high (dec (count elements))]
    (let [m (middle low high)
          v (nth elements m)]
      (cond
        (> low high) (throw (Exception. "not found")) ;; I keep forgottin how to launch exception
        (= v to-find) m
        (> v to-find) (recur low (dec m))
        (< v to-find) (recur (inc m) high)))))
