(ns triangle)

;; This exercise will be simpler anc coincise if I use partition I think.

(defn is-valid? [s1 s2 s3] ;; <- arglist goes here
  ;; your code goes here
  (if (reduce (fn [acc elem] (if (>= 0 elem) (and acc elem) false)) true [s1 s2 s3]) ;; can't use list comprehension here
    false
    (do
    (let [s12 (+ s1 s2)
          s13 (+ s1 s3)
          s23 (+ s2 s3)]
      (and (>= s12 s3) (>= s13 s2) (>= s23 s1))))))

(defn equilateral? [s1 s2 s3] ;; <- arglist goes here
  ;; your code goes here
  (if (is-valid? s1 s2 s3)
    (= s1 s2 s3)
    false))

(defn isosceles? [s1 s2 s3] ;; <- arglist goes here
  ;; your code goes here
  (if (is-valid? s1 s2 s3)
    (or (= s1 s2) (= s1 s3) (= s2 s3))
    false))

(defn scalene? [s1 s2 s3] ;; <- arglist goes here
  ;; your code goes here
  (if (is-valid? s1 s2 s3)
    (and (not= s1 s2) (not= s1 s3) (not= s2 s3))
    false))
