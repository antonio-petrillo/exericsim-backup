(ns matching-brackets)

(defn valid? [s]
  (loop [stack ()
         [c & rs] s]
    (cond (nil? c) (empty? stack)
          (#{\( \[ \{} c) (recur (conj stack c) rs)
          (#{\) \] \}} c) (if (not= c ({\( \) \[ \] \{ \}} (first stack)))
                            false
                            (recur (rest stack) rs))
          :else (recur stack rs))))
