(ns sieve)
;; I don't like my solution, I think it consume to much memory and redo to much calculation.
;; Obviously I could have take another approach, but I have sticked to this one because I find interesting the functions fmap, fmap-key and mark
;; I'm pretty sure that I will find a one liner in community solution 🙂.

;; V1, inefficient consume a lot of memory, maybe I should retry with a transducer
;; (defn unmarked? [m k]
;;   (= :unmarked (m k)))

;; (defn fmap [f m]
;;   (into {} (for [[k v] m] {k (f v)})))

;; (defn fmap-key [f m]
;;   (into (sorted-map) (for [[k _] m] {k (f k)})))
;; (defn mark [m k]
;;   (fmap-key #(if (and (zero? (mod % k)) (not= % k)) :marked (m %)) m))

;; (defn sieve [up-to]
;;   (loop [candidates (into (sorted-map) (zipmap (range 2 (inc up-to)) (repeat :unmarked)))
;;          indexes (range 2 (inc (quot up-to 2)))]
;;     (if (empty? indexes)
;;       (mapv first (filter #(unmarked? candidates (first %)) candidates))
;;       (let [[head & tail] indexes]
;;         (if (unmarked? candidates head)
;;           (recur (mark candidates head) tail)
;;           (recur candidates tail))))))



;; V2 much better, this is also a proper version of erathostens (I hope I writed it right) sieve
(defn sieve [up-to]
    (loop [[head & tail] (range 2 (inc up-to)) primes []]
      (if (nil? head)
        primes
        (recur (filter #(pos? (mod % head)) tail) (conj primes head)))))
