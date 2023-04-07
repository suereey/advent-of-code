(ns advent-of-code.2020.day1
  (:require [clojure.string :as string]
            [clojure.set :as cljset]))

(defn intify
  [int-str]
  (Integer/parseInt int-str))

(defn file->entry-s
  [filename]
  (->> (-> filename
           (slurp)
           (string/split #"\n"))
       (mapv intify)))

(defn entry-complement-s
  [complement entry-s]
  (->> entry-s
       (map (partial - complement))
       (into #{})))

(defn two-sum-s
  [target-sum entry-s]
  (let [complements (entry-complement-s target-sum entry-s)
        two-sum-s   (clojure.set/intersection complements
                                              (set entry-s))]
    two-sum-s))

(defn complement-product
  [target n]
  (* n
     (- target n)))

;; Rich Comment Block
(comment

  ;; read the file into a sequence
  (do (def sample-entry-s (file->entry-s "resources/2020/day1/input-sample.txt"))
      sample-entry-s)
  #_=> [1721 979 366 299 675 1456]

  (->> [1 4 9]
       (entry-complement-s 20))
  #_=> #{19 11 16}

  (intersection #{1 2 3}
                #{1 6 18 9})
  #_=> #{1}

  (two-sum-s 10 [4 1 14 6])
  #_=> #{4 6}

  (->> sample-entry-s
       (two-sum-s 2020)
       (mapv (partial complement-product 2020))
       first)

  (two-sum-s 2020 sample-entry-s)
  #_=> #{299 1721}
  (complement-product 2020 299)
  #_=> 514579
  (map #(complement-product 2020 %)  (two-sum-s 2020 sample-entry-s))
  #_=> (514579 514579)
  ; practice using partial instead of #()
  (mapv #(complement-product 2020 %)  (two-sum-s 2020 sample-entry-s))
  #_=> [514579 514579]
  (mapv (partial complement-product 2020) (two-sum-s 2020 sample-entry-s))
  #_=> [514579 514579]

  (def entry-2020complement-s (partial entry-complement-s 2020))

  ;; practice filter sample2, keep the value which exsist in sample 1
  (def sample1 [1721 979 366 299 675 1456])
  (def sample2 (list 299 1041 1654 1721 1345 564))
  (filter #(contains? (set sample1) %) sample2)
  #_=> (299 1721)

  ;; practice intify sample
  (mapv intify ["-1" "100" "1726"])
  #_=> [-1 100 1726]

  ;; practice filter
  (def my-vector [1 2 3 4])
  (filter #(= % 1) my-vector)
  #_=> (1)
  (filter #(= % 0) my-vector)
  #_=> ()

  ;; note from luckasz
  (let [entries         [1 2 3 4]
        compl           (->> (map #(- 2020 %) entries)
                             (into #{}))
        two-sum-entries (map compl entries)]
    two-sum-entries)

  (->> (let [entries (range 10000)]
         (for [x entries
               y entries
               z entries]
           [x y]))
       (take 10))

  (->> (map (partial filter-target-val 2020))
       (filter nonnil?)
       (take 2)
       (reduce *))
  (->> sample-entry-s
       (two-sum-s 2020)
       #_(entry-complement-s 2020)
       #_(clojure.set/intersection (set sample-entry-s))
       (mapv (partial complement-product 2020))
       first)
  )
