(ns largest-series-product)

(defn largest-product [len digits]
  (cond (or (< (count digits) len) (< len 0)) (throw (IllegalArgumentException. "rejects span longer than string length"))
        (< len 0) (throw (IllegalArgumentException. "rejects negative length"))
        (and ((complement zero?) len) (empty? digits)) (throw (IllegalArgumentException. "rejects empty string and non zero span"))
        (re-find #"[^0-9]" digits) (throw (IllegalArgumentException. "rejects invalid character in digits"))
        (zero? len) 1
        :else (->> digits
                   (partition len 1)
                   (map #(apply * (map (fn [digit] (Integer/parseInt (str digit))) %)))
                   (apply max))))
