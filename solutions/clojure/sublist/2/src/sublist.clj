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

;; This was my first approach, but there is an error.
;; Here I search if all the element are contained in the second list but this is different from checking if the first list, the whole list, is contanied in the second

;; (defn sublist? [list1 list2]
;;   (reduce (fn [curr elem] (and curr (some #(= elem %) list2))) true list1))


;; This is a little bit junky but it works, I'm pretty sure that there it is clojure core function for this, I need to find out.
(defn sublist? [list1 list2]
  (let [sublist2 (drop-while #(not= (first list1) %) list2)
        list1-size (count list1)
        sublist2-size (count list2)]
    (if (= list1 (take list1-size sublist2))
      true
      (if (> list1-size sublist2-size)
        false
        (sublist? list1 (rest sublist2))))))
