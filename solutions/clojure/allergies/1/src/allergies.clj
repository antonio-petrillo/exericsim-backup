(ns allergies)

(def power-of-2
  (into [] (take 8 (iterate #(bit-shift-left % 1) 1))))

(def allergies-db
  (let [allergies [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats]]
    (zipmap power-of-2 allergies)))

(defn allergies [score]
  (into []
        (for [bit (range 0 8) :when (bit-test score bit)]
          (allergies-db (nth power-of-2 bit)))))

(defn allergic-to? [score allergen]
  (let [allergies [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats]
        score-db (zipmap allergies (range 0 7))
        bit (score-db allergen)]
    (if (nil? bit)
      false
      (bit-test score (score-db allergen)))))
