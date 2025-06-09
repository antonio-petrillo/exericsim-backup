(ns kindergarten-garden)

(def students
  ["Alice"
   "Bob"
   "Charlie"
   "David"
   "Eve"
   "Fred"
   "Ginny"
   "Harriet"
   "Ileana"
   "Joseph"
   "Kincaid"
   "Larry"])

(defn char->plant [c]
  (cond (= c \V) :violets
        (= c \R) :radishes
        (= c \C) :clover
        (= c \G) :grass))

(defn garden
  ([garden-config students]
   (let [rows (->> garden-config
                   (clojure.string/split-lines)
                   (map #(map char->plant (seq %)))
                   (map (partial partition 2))
                   (apply mapv #(into [] (concat %1 %2))))]
     (zipmap (mapv #(keyword (.toLowerCase %)) (sort students)) rows)))
  ([garden-config]
   (garden garden-config students)))
