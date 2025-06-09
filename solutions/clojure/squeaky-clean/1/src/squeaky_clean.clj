(ns squeaky-clean
  (:require [clojure.string :as str]))

(defn replace-whitespace [s]
  (str/replace s #"\s" "_"))

(defn replace-control [s]
  (str/replace s #"\p{Cntrl}" "CTRL"))

(defn replace-kebab [s]
  (str/replace s #"-\p{javaLowerCase}" #(str (Character/toUpperCase (second %)))))

(defn replace-non-letters [s]
  (str/replace s #"[^\p{javaLowerCase}\p{javaUpperCase}|_]" ""))

(defn replace-lowercase-greek [s]
  (str/replace s #"[α-ω]" ""))

(defn clean
  [s]
  (-> s
      replace-whitespace
      replace-control
      replace-kebab
      replace-non-letters
      replace-lowercase-greek))
