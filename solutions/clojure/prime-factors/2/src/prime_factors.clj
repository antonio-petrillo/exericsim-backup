(ns prime-factors)

(defn of [n]
  (loop [factor 2
        result []
        num n]
      (cond (= 1 num) result
            (zero? (mod num factor)) (recur factor (conj result factor) (quot num factor))
            :else (recur (inc factor) result num))))

;; My first approach was to precompute the primes with the sieve eratosthenes,
;; but with number with huge factor this was to heavy to compute
;; (with the sieve I have to compute all the primes from 2 to the N), even with lazyseq this is humongous on the last 2 test.
;; https://www.youtube.com/watch?v=tPQaem-SgKw&t=421s here you can find an interesting video on lazyseq sieve.
;; Then I got my head stuck to find a workaround on this problem and then I realized that the solution is simpler but really interesting.
;;
;; There is a lot (for me, obviously) to think about here:
;; Why factor is prime?
;;
;; Not every value used for factor is prime, for example if n is 5 it will assume the value 4 but it will not be a factor
;;
;; So how this work.
;; The interesting thing about primes is that they come before their multiples, i.e. all the even come after 2
;; Since the start value for factor is 2 this mean that it will detect all the 2 factors of the number
;;
;; When a number is divisible by the factor `(zero (mod num factor))` it is added to the result
;; and then the input is reduced `(quot num factor)`
;;
;; The factor is increased only when it not divide the input
;;
;; If the factor is not prime then it will never be added
;; because it's factors will be already eliminated in previous call
;;
;; Example: input 160 result [] factor 2
;;
;; Divisible by `2`
;; Recur with: input 80 result [2] factor 2
;;
;; Divisible by `2`
;; Recur with: input 40 result [2 2] factor 2
;;
;; Divisible by `2`
;; Recur with: input 20 result [2 2 2] factor 2
;;
;; Divisible by `2`
;; Recur with: input 10 result [2 2 2 2] factor 2
;;
;; Not divisible by `2`
;; Recur with: input 40 result [2 2 2 2 2] factor 2
;;
;; Not divisible by `3`
;; Recur with: input 40 result [2 2 2 2 2] factor 3
;;
;; `NOTE`: here all the even number are gone hence
;;    there is no chance to find a multiple of 4
;;
;; Not divisible by `4`
;; Recur with: input 40 result [2 2 2 2 2] factor 4
;;
;; Divisible by `5`
;; Recur with: input 40 result [2 2 2 2 2] factor 5
;;
;; Base case
;; End => [2 2 2 2 2 5]
