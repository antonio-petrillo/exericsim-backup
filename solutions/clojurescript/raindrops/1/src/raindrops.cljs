(ns raindrops)


(defn convert [input]
  (let [factor3? (if (zero? (mod input 3)) "Pling" nil)
        factor5? (if (zero? (mod input 5)) "Plang" nil)
        factor7? (if (zero? (mod input 7)) "Plong" nil)]
    (if (or factor3? factor5? factor7?)
      (reduce (fn [acc elem] (if elem (str acc elem) acc)) "" [factor3? factor5? factor7?])
      (str input))))
