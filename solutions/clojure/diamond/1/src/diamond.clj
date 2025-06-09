(ns diamond)

(def letters (into [] (map char (range (int \A) (inc (int \Z))))))


(def letters-spaces (zipmap
              letters
              (into [0] (take 25 (iterate #(+ % 2) 1)))))

(defn make-spaces [amount]
  (apply str (repeat amount \space)))

(defn make-row [letter]
  (let [amount (letters-spaces letter)]
    (if (zero? amount)
      (str letter)
      (str letter (make-spaces amount) letter))))

(defn add-padding [amount row]
  "add `amount` of padding, `space`, to both end of the row"
  (let [padding (make-spaces amount)]
    (str padding row padding)))

(defn diamond [letter]
  (let [center-row (make-row letter)
        index (- (int letter) 66)
        paddings (iterate inc 1)
        lower-part (->> (reduce
                        (fn [rows letter]
                          (conj rows (make-row (get letters letter))))
                        [] (range index -1 -1))
                       (map add-padding paddings))]
    (into [] (concat (reverse lower-part) [center-row] lower-part ))))
