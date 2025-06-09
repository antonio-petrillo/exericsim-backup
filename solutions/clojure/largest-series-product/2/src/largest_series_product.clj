(ns largest-series-product)

(defn largest-product [len digits]
  (cond (< len 0) (throw (IllegalArgumentException. "span must not be negative"))
        (or (< (count digits) len) (< len 0)) (throw (IllegalArgumentException. "span must be smaller than string length"))
        (and ((complement zero?) len) (empty? digits)) (throw (IllegalArgumentException. "span must be smaller than string length"))
        (re-find #"[^0-9]" digits) (throw (IllegalArgumentException. "digits input must only contain digits"))
        (zero? len) 1
        :else (->> digits
                   (partition len 1)
                   (map #(apply * (map (fn [digit] (Integer/parseInt (str digit))) %)))
                   (apply max))))
