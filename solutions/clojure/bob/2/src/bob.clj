(ns bob)

(defn all-capitals? [s]
  (reduce (fn [acc elem](and acc (if (<= 97 (int elem) 122) false true))) true (seq s)))

(defn response-for [s] ;; <- arglist goes here
  ;; your code goes here
  (let [ s (clojure.string/trim s)
        is-question (re-find #"\?$" s)
        is-all-capitals (all-capitals? s)
        has-letter (re-find #"[a-zA-Z]" s)]
    (cond (= s "") "Fine. Be that way!"
          (and has-letter is-question is-all-capitals) "Calm down, I know what I'm doing!"
          (and has-letter is-all-capitals) "Whoa, chill out!"
          is-question "Sure."
          :else "Whatever.")))
