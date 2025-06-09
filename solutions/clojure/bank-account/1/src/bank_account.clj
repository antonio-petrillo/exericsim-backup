(ns bank-account)
;; this exercise have an huge simplification, it's not possible to do transfer!

(defn open-account []
  (atom {:status :open :balance 0}))

(defn closed? [account]
  (not= :open (@account :status)))


(defn close-account [account]
  (if-not (closed? account)
    (swap! account assoc :status :closed)))

(defn get-balance [account]
  (if-not (closed? account)
    (@account :balance)))

(defn update-balance [account amount]
  (if-not (closed? account)
    (swap! account update :balance #(+ % amount))))
