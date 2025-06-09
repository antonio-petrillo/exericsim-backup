(ns bob)

;; (defn all-capital? [s]
;;   (let [letters (filter #(<= 65 (int %) 132) s)
;;         capital (filter #(<= 65 (int %) 90) letters)]
;;     (= (count letters) (count capital))))

;; (defn all-capital? [s]
;;   (let [letters (filter #(<= 65 (int %) 132) s)]
;;     (reduce (fn [acc elem] (and acc (<= 65 (int elem) 90))) true letters)))

;; (defn all-capitals [s]
;;   (reduce (fn [acc elem] (and acc (if (<= 91 (int elem) 132) false true))) true (seq s)))

(defn all-capitals [s]
  (reduce #((and %1 (if (<= 91 (int %2) 132) false true))) true (seq s)))

(defn response-for [s] ;; <- arglist goes here
  ;; your code goes here
  (let [is-question (re-find #"?$" s)
        is-all-capital (all-capital? s)]
    (cond (and is-question is-all-capital) "Calm down, I know what I'm doing!"
          is-question "Sure"
          is-all-capital "Whoa, chill out!"
          :else "Whatever.")))
