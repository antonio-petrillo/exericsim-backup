(ns zipper)

(defn from-tree [tree]
  {:tree tree :path [:tree] :focus tree})

(defn value [zipper]
  (:value (:focus zipper)))

(defn left [zipper]
  (when (:left (:focus zipper))
    (-> zipper
        (update :path #(conj % :left))
        (assoc :focus (:left (:focus zipper))))))

(defn right [zipper]
  (when (:right (:focus zipper))
    (-> zipper
        (update :path #(conj % :right))
        (assoc :focus (:right (:focus zipper))))))

(defn to-tree [zipper]
  (:tree zipper))

(defn up [zipper]
  (when-not (= [:tree] (:path zipper))
    (let [path (into [] (drop-last (:path zipper)))
          focus (get-in zipper path)]
      (-> zipper
          (assoc :path path)
          (assoc :focus focus)))))

(defn set-value [zipper value]
  (let [focus (assoc-in zipper [:focus :value] value)]
    (assoc-in zipper (:path zipper) (:focus focus))))

(defn set-left [zipper left]
  (-> (assoc-in zipper [:focus :left] left)
      (assoc-in (conj (:path zipper) :left) left)))

(defn set-right [zipper right]
  (-> (assoc-in zipper [:focus :right] right)
      (assoc-in (conj (:path zipper) :right) right)))
