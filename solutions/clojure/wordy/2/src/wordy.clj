(ns wordy
  (:require [clojure.string :as str]))

(def ^:private ops
  {"plus" +
   "minus" -
   "multiplied" *
   "divided" /
   "raised" (fn [x y] (long (Math/pow x y))) })

(defn compute [tokens]
  (loop [[token & tokens] tokens op nil num nil]
    (cond (nil? token) (if op
                         (throw (IllegalArgumentException. "syntax error"))
                         num)
          (= token "cubed") (throw (IllegalArgumentException. "unknown operation"))
          (ops token) (cond (nil? num) (throw (IllegalArgumentException. "syntax error"))
                            (not (nil? op)) (throw (IllegalArgumentException. "syntax error"))
                            :else (recur tokens (ops token) num))
          :else (cond (and (nil? num) (nil? op)) (recur tokens op (parse-long token))
                      (nil? op) (throw (IllegalArgumentException. "syntax error"))
                      :else (recur tokens nil (op num (parse-long token)))))))

(defn evaluate [s]
  (let [tokens (re-seq #"-?\d+|plus|minus|multiplied|divided|raised|cubed" s)]
    (if (empty? tokens)
      (throw (IllegalArgumentException. "syntax error"))
      (compute tokens))))
