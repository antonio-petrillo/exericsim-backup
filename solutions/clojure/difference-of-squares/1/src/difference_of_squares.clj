(ns difference-of-squares)

(defn sum-of-squares [up-to]
  (quot (* up-to (+ 1 up-to) (+ 1 (* 2 up-to))) 6))

(defn square-of-sum [up-to]
  (let [sum (quot (* up-to (+ 1 up-to)) 2)]
    (* sum sum)))

(defn difference [up-to]
  (- (square-of-sum up-to) (sum-of-squares up-to)))

(defn square-of-sum-without-formula [up-to]
  (let [sum (apply + (range 1 (+ 1 up-to)))]
    (* sum sum)))

(defn sum-of-squares-without-formula [up-to]
  (apply + (map #(* % %) (range 1 (+ 1 up-to)))))
