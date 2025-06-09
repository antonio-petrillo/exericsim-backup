(ns pangram)

(defn pangram? [s] ;; <- arglist goes here
  ;; your code goes here
  ;; (= 26
  ;;    (count (reduce
  ;;     (fn [acc char]
  ;;       (if (Character/isLetter char)
  ;;         (let [char (Character/toLowerCase char)]
  ;;           (conj acc char))
  ;;         acc))
  ;;     #{} s))))
  (->> (.toLowerCase "The quick brown fox jumps over the lazy dog.")
       (set)
       (filter #(Character/isLetter %))
       (count)
       (= 26)))
