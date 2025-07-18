(ns grade-school)

(defn grade [school grade]
  (let [class (school grade)]
    (if (nil? class)
      []
      class)))

(defn add [school name grade]
  (if (nil? (school grade))
    (assoc school grade (vector name))
    (update school grade #(conj % name))))

(defn sorted [school]
  (into
   (sorted-map)
   (for [[grade students] school] [grade (into [] (sort students))])))
