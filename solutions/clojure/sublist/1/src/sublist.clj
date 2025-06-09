(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (let [is-sublist (sublist? list1 list2)
        is-superlist (sublist? list2 list1)]
    (cond
      (and is-sublist is-superlist) :equal
      is-sublist :sublist
      is-superlist :superlist
      :else :enequal)))


(defn sublist? [list1 list2]
  (reduce (fn [curr elem] (and curr (some #(= elem %) list2))) true list1))
