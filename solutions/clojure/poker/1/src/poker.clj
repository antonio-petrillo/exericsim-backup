(ns poker
  (:require [clojure.string :as s]))

(def seeds {"H" :hearth
             "C" :club
             "S" :spade
             "D" :diamond})

(def card-values (zipmap '("2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A") (range 1 14)))

(def ranks (zipmap '(:high-card :pair :two-pair :three-of-a-kind :straight :flush :full-house :four-of-a-kind :straight-flush :five-of-a-kind) (range)))

(defn parse-card [card]
  (let [parsed (re-find #"([0-9JQKA]+)([HCSD])" card)
        value (nth parsed 1)
        seed (nth parsed 2)]
    {:value (card-values value) :seed (seeds seed)}))

(defn parse-hand [hand]
  (mapv parse-card (s/split hand #"\s")))

(defn max-card-value [hand]
  (apply max (map :value hand)))

(defn flush? [hand]
  (let [seed ((first hand) :seed)]
    (when (= 5 (->> hand (filter #(= seed (% :seed))) count))
      {:type :flush :values (->> hand (map :value) (sort >))})))

(defn straight? [hand]
  (let [values (->> hand (map :value) (sort >))
        max (apply max values)
        ace? (some #{13} values)
        two? (some #{2} values)]
    (cond (= values (range max (- max 5) -1)) {:type :straight :values values}
          (and ace? two? (= values '(13 4 3 2 1))) {:type :straight :values '(4 3 2 1 0)})))

(defn straight-flush? [hand]
  (let [flush (flush? hand)
        straight (straight? hand)]
    (when (and flush straight)
      (assoc straight :type :straight-flush))))

(defn- get-pair-of-three-of-a-kind-value [grouped]
  (let [sorted-group  (->> grouped (sort-by (comp count second) >))
        pair (->> sorted-group first first (conj []))
        remaining (sort > (map first (rest sorted-group)))]
    (into pair remaining)))

(defn- get-2-pair-value [grouped]
  (let [pairs (->> (take 2 grouped) keys (sort >) (into []))
        kicker (first (last grouped))]
    (conj pairs kicker)))

(defn couple? [hand]
  (let [grouped (->> hand (group-by :value))
        values (->> grouped (map first) (sort >))
        grouped-count (->> grouped (sort-by (comp count second) >) (map (comp count second)))]
    (case grouped-count
      ((5)) {:type :five-of-a-kind :values values}
      ((4 1)) {:type :four-of-a-kind :values (map first grouped)}
      ((3 2)) {:type :full-house :values (->> grouped (sort-by (comp count second) >) (map first))}
      ((3 1 1)) {:type :three-of-a-kind :values (get-pair-of-three-of-a-kind-value grouped)}
      ((2 2 1)) {:type :two-pair :values (get-2-pair-value grouped)}
      ((2 1 1 1)) {:type :pair :values (get-pair-of-three-of-a-kind-value grouped)}
      ((1 1 1 1 1)) {:type :high-card :values values})))

(defn rank-hand [hand]
  (let [straight-flush (straight-flush? hand)
        couples (couple? hand)
        flush (flush? hand)
        straight (straight? hand)]
    (cond (= :five-of-a-kind (:type couples)) couples
          straight-flush straight-flush
          (= :four-of-a-kind (:type couples)) couples
          (= :full-house (:type couples)) couples
          flush flush
          straight straight
          (= :three-of-a-kind (:type couples)) couples
          (= :three-of-a-kind (:type couples)) couples
          (= :two-pair (:type couples)) couples
          (= :pair (:type couples)) couples
          (= :high-card (:type couples)) couples)))

(defn compare-value [vals1 vals2]
  (loop [[val1 & vals1] vals1 [val2 & vals2] vals2]
      (cond (> val1 val2) 1
            (= val1 val2) (if (nil? vals1) 0  (recur vals1 vals2))
            :else -1)))

(defn get-best-rank [hands]
  (loop [best (first hands) [hand & remaining] (rest hands)]
    (if (nil? hand)
      best
      (let [order (compare-value (hand :values) (best :values))]
        (cond (= (best :type) (hand :type)) (if (pos? order) (recur hand remaining) (recur best remaining))
              (> (ranks (hand :type)) (ranks (best :type))) (recur hand remaining)
              :else (recur best remaining))))))

(defn best-hands [hands]
  (let [rankeds (map (fn [hand] {:rank (rank-hand (parse-hand hand)) :hand hand}) hands)
        ranks (map :rank rankeds)
        best-rank (get-best-rank ranks)]
    (map :hand ((group-by :rank rankeds) best-rank))))
