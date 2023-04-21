(ns advent-of-code.2020.day1-re-do)

(defn file->entry-s
  [filename]
  (->> (-> filename
           (slurp)
           (clojure.string/split #"\n"))
       (mapv #(Integer/parseInt %))))

(defn target-val
  [target-sum entry-val]
  (- target-sum entry-val))

(defn target-val-s
  [target-sum entry-val-s]
  (mapv #(target-val target-sum %) entry-val-s))

(defn complement
  [entry-s target-val-s]
  (clojure.set/intersection (set entry-s) (set target-val-s)))

;; Rich Comment Block
(comment

  (do (def sample-entry-s (file->entry-s "resources/2020/day1/input-sample.txt"))
      sample-entry-s)
  #_=> [1721 979 366 299 675 1456]

  (- 2020 1721)
  #_=> 299

  (target-val 2020 1721)
  #_=> 299

  (mapv #(target-val 2020 %) [1721 979 366 299 675 1456])
  #_=> [299
        1041 1654 1721 1345 564]
  ;(inc 2)
  ;
  ;(mapv inc [1 2 3 4])
  ;(map inc '(1 2 3))

  (target-val-s 2020 [1721 979 366 299 675 1456])
  (set [1721 979 366 299 675 1456])
  #{1 2 3 4}
  #{4 5 6}


  (clojure.set/intersection #{1 2 3 4} #{4 5 6})

  (->> sample-entry-s
       (target-val-s 2020)
       (complement sample-entry-s)
       (reduce *)))
