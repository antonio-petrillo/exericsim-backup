(ns log-levels
  (:require [clojure.string :as str]))

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (let [log-message (subs s (.indexOf s " "))]
    (str/trim log-message)))

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (let [log-level (re-find #"\[.*\]" s)
        log-level (subs log-level 1 (dec (count log-level)))]
    (.toLowerCase log-level)))

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")")
  )
