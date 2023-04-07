(ns advent-of-code.2020.day1-part2-2sum
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

;; Rich Comment Block
(comment

  (do (def sample-entry-s (file->entry-s "resources/2020/day1/input-sample.txt"))
      sample-entry-s)
  #_=> [1721 979 366 299 675 1456]

  (->> sample-entry-s
       (map #(two-sum-s (- 2020 %) sample-entry-s))
       (set)
       (reduce clojure.set/union)
       (reduce *))
  #_=> 241861950

  (->> [1 4 9]
       (entry-complement-s 20))
  #_=> #{19 11 16}

  ;; lei practice remove
  (remove #{1} [1 4 9])
  #_=> (4 9)
  (two-sum-s 10 [4 1 5 14 6])
  #_=> #{4 6 5}
  (two-sum-s 10 (remove #{5} [4 1 5 14 6]))
  #_=> #{4 6}

  ;; apply 2 sum to 3 sum
  (map #(two-sum-s (- 2020 %) sample-entry-s) sample-entry-s)
  #_=> (#{} #{366 675} #{979 675} #{} #{979 366} #{})
  ;; question, how to use partial instead of the above function

  (map #(two-sum-s (- 2020 %) (remove #{%} sample-entry-s)) sample-entry-s)
  #_=> (#{} #{366 675} #{979 675} #{} #{979 366} #{})
  (do (def test-list '(#{} #{366 675} #{979 675} #{} #{979 366} #{}))
      test-list)
  #_=> (#{} #{366 675} #{979 675} #{} #{979 366} #{})

  (set test-list)
  #_=> #{#{979 675} #{} #{979 366} #{366 675}}
  (reduce clojure.set/union (set test-list))
  #_=> #{979 366 675}
  (do (def final-set (reduce clojure.set/union (set test-list)))
      final-set)
  #_=> #{979 366 675}
  (reduce * final-set)
  #_=> 241861950

  (map (partial inc) [1 2 3])
  (map #(inc %) [1 2 3])
  )

