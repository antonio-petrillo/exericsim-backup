(ns interest-is-interesting)

(defn interest-rate
  "TODO: add docstring"
  [balance]
  (cond (< balance 0M) -3.213
        (< balance 1000M) 0.5
        (< balance 5000M) 1.621
        :else 2.475))

(defn annual-balance-update
  "TODO: add docstring"
  [balance]
  (let [interest-rate (bigdec (Math/abs (interest-rate balance)))
        ;; See the example on exercism, tax free of 2.5, means := 0.025%
        earnings (* balance interest-rate 0.01M)]
  (+ balance earnings)))

(defn amount-to-donate
  "TODO: add docstring"
  [balance tax-free-percentage]
  (if (pos? balance)
    (int (* balance tax-free-percentage 0.01 2))
    0))

;; One interesting thing is:
;; I forgot to add the 2 in the multiplication,
;; and on the example with 550.5M 2.5 input I got /13/ when the expected is 27
;; here, before the cast to int, the multiplication by two change the rounded result.
;; maybe instead of 13 is something like 13.6, but now I'm to tired to try.
