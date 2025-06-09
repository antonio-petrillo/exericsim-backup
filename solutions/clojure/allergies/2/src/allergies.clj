(ns allergies)

(def allergies-map
  { :eggs 1
    :peanuts 2
    :shellfish 4
    :strawberries 8
    :tomatoes 16
    :chocolate 32
    :pollen 64
    :cats 128 })

(def allergies-db
  [:eggs
   :peanuts
   :shellfish
   :strawberries
   :tomatoes
   :chocolate
   :pollen
   :cats])

(defn allergic-to? [score allergen]
  (-> allergen
      allergies-map
      (bit-and score)
      zero?
      not))

(defn allergies [score]
  (->> (range 0 8)
       (filter (partial bit-test score))
       (map allergies-db)))
