(ns advent-of-code.2020.day1_part2_combination
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

(defn entry-combinations
  [entry-s]
  (for [x entry-s
        y entry-s
        z entry-s]
    [x y z]))

(defn sumed+combination
  [entry-combination]
  {:sum    (reduce + entry-combination)
   :window entry-combination})

(defn get-combination
  [target-sum sumed+combination]
  (filter #(= (:sum %) target-sum) sumed+combination))

;; Rich Comment Block
(comment
  (do (def sample-entry-s (file->entry-s "resources/2020/day1/input.txt"))
      sample-entry-s)
  #_=> [1721 979 366 299 675 1456]
  (->> sample-entry-s
       (entry-combinations)
       (map (partial sumed+combination))
       (get-combination 2020)
       (map :window)
       (map #(reduce * %))
       first
       )
  (take 3 (entry-combinations sample-entry-s))
  #_=> ([1721 1721 1721] [1721 1721 979] [1721 1721 366])

  (do (def list-combinations (entry-combinations sample-entry-s))
      list-combinations)
  (take 3 list-combinations)
  #_=> ([1721 1721 1721] [1721 1721 979] [1721 1721 366])

  (reduce + [1721 1721 1721])
  #_=> 5163
  (map #(sumed+combination %) list-combinations)
  (map (partial sumed+combination) list-combinations)
  (do (def indexed-list (map (partial sumed+combination) list-combinations))
      indexed-list)
  (take 3 indexed-list)
  #_=> ({:sum 5163, :window [1721 1721 1721]} {:sum 4421, :window [1721 1721 979]} {:sum 3808, :window [1721 1721 366]})

  (get-combination 2020 indexed-list)
  #_=> ({:sum 2020, :window [979 366 675]}
        {:sum 2020, :window [979 675 366]}
        {:sum 2020, :window [366 979 675]}
        {:sum 2020, :window [366 675 979]}
        {:sum 2020, :window [675 979 366]}
        {:sum 2020, :window [675 366 979]})

  (do (def selected-list (get-combination 2020 indexed-list))
      selected-list)
  (map :window selected-list)
  #_=> ([979 366 675] [979 675 366] [366 979 675] [366 675 979] [675 979 366] [675 366 979])
  (map #(reduce * %) (map :window selected-list))
  #_=> (241861950 241861950 241861950 241861950 241861950 241861950)
  )