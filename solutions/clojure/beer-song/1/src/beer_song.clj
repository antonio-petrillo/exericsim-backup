(ns beer-song)


(defn first-line [n]
  (let [x (cond (= n 1) "1 bottle "
                    (= n 0) "No more bottles "
                    :else   (str n " bottles "))]
    (clojure.string/capitalize (str x "of beer on the wall, " x "of beer.\n"))))

(defn second-line [n]
  (let [[x y] (if (= n 0)
                     ["Go to the store and buy some more, " "99 bottles "]
                     [(str "Take " (if (= n 1) "it " "one ") "down and pass it around, ") (if (= n 1) "no more bottles " (str (- n 1) (if (= (- n 1) 1) " bottle " " bottles ")))])]
    (str x y "of beer on the wall.\n")))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (str (first-line num) (second-line num)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (clojure.string/join "\n" (map verse (range start (- end 1) -1)))))
