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

(defn summed-combination-m-s
  [entry-combination]
  {:sum     (reduce + entry-combination)
   :entries entry-combination})

(defn compl-for-target?
  [target-sum {:keys [sum] :as _summed-combination-m}]
  (= sum
     target-sum))


(defn part2-answer
  [compl-sum]
  (reduce * compl-sum))

;; Rich Comment Block
(comment

  (do (def sample-entry-s (file->entry-s "resources/2020/day1/input.txt"))
      sample-entry-s)
  #_=> [1721 979 366 299 675 1456]



  (->> sample-entry-s
       (entry-combinations)
       (map summed-combination-m-s)
       (filter (partial compl-for-target? 2020))
       (map :entries)
       (map part2-answer)
       first)
  #_=>


  (take 3 (entry-combinations sample-entry-s))
  #_=> '([1721 1721 1721] [1721 1721 979] [1721 1721 366])


  (do (def list-combinations (entry-combinations sample-entry-s))
      (take 3 list-combinations))
  #_=> '([1721 1721 1721] [1721 1721 979] [1721 1721 366])


  (reduce + [1721 1721 1721])
  #_=> 5163


  (map summed-combination-m-s list-combinations)

  (map summed-combination-m-s [[1 3 4] [5 3 1] [4 3 2]])
  #_=> ({:sum 8, :entries [1 3 4]}
        {:sum 9, :entries [5 3 1]}
        {:sum 9, :entries [4 3 2]})



  (map (partial sumed+combination) list-combinations)
  (do (def indexed-list (map (partial sumed+combination) list-combinations))
      indexed-list)
  (take 3 indexed-list)
  #_=> ({:sum 5163, :window [1721 1721 1721]} {:sum 4421, :window [1721 1721 979]} {:sum 3808, :window [1721 1721 366]})



  (->> [[1 3 4] [5 3 1] [4 3 2]]
       (map summed-combination-m-s)
       (filter (partial compl-for-target? 2020)))
  #_

  (get-combination 2020 indexed-list)
  #_=> '({:sum 2020, :window [979 366 675]}
         {:sum 2020, :window [979 675 366]}
         {:sum 2020, :window [366 979 675]}
         {:sum 2020, :window [366 675 979]}
         {:sum 2020, :window [675 979 366]}
         {:sum 2020, :window [675 366 979]})

  (do (def selected-list (get-combination 2020 indexed-list))
      selected-list)
  (mapv :window selected-list)
  #_=> '([979 366 675] [979 675 366] [366 979 675] [366 675 979] [675 979 366] [675 366 979])

  (mapv #(reduce * %) (map :window selected-list))
  #_=> '(241861950 241861950 241861950 241861950 241861950 241861950)


  )