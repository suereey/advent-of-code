(ns advent-of-code.2020.day1)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))



(defn intify
  [input-string-sample]
  (map #(Integer/parseInt %) input-string-sample))


(defn filter-target-val
  [cur-entry coll]
  (let [expect-entry (- 2020 cur-entry)]
    (filter #(= % (expect-entry cur-entry)) coll)))


(let [entries         [1 2 3 4]
      compl           (->> (map #(- 6 %) entries)
                           (into #{}))
      two-sum-entries (map compl entries)]
  two-sum-entries)

(->> (let [entries (range 10000)]
       (for [x entries
             y entries
             z entries]
         [x y]))
     (take 10))


(defn string-to-int
  [input-string-sample]
  (map #(Integer/parseInt %) input-string-sample))

(defn find-pair
  [input-int-sample]
  (loop [coll input-int-sample
         res  -1]
    (if (> res 0)
      res
      (let [cur-entry (first coll)
            temp-list (filter-target-val cur-entry coll)]
        (if (empty? temp-list)
          (recur (rest coll) res)
          (let [new-res (* cur-entry (- 2020 cur-entry))]
            (recur (rest coll) new-res)))))))

(->> (map (partial filter-target-val 2020))
     (filter nonnil?)
     (take 2)
     (reduce *))




(defn check-entry-in-set
  [cur-set expect-entry cur-entry]
  (if (cur-set expect-entry)
    (* expect-entry cur-entry)
    (conj cur-set cur-entry)))

(defn filter-expect-entry
  [cur-entry coll]
  (filter #(= % (expect-entry (cur-entry))) coll))

;; Rich Comment Block
(comment
  ;; read the file into a sequence

  (do (def input-sample (file->seq "resources/2020/day1/input-sample.txt"))
      input-sample)
  #_=>=> ["1721" "979" "366" "299" "675" "1456"]



  (->> input-sample
       (string-to-int)
       (find-pair))


  (def test-sample ["1721" "979" "366" "299" "675" "1456"])
  test-sample
  #_=> ["1721" "979" "366" "299" "675" "1456"]


  (find-pair test-sample)
  (filter-expect-entry 1721 [1721 979 366 299 675 1456])
  (filter #(= % (expect-entry (1721))) coll)

  test-sample
  #_=> ["1721" "979" "366" "299" "675" "1456"]


  (map #(Integer/parseInt %) test-sample)
  #_=> (1721 979 366 299 675 1456)
  (->>
    (file->seq "resources/2020/day1/input-sample.txt")
    (string-to-int)
    )
  #_=> (1721 979 366 299 675 1456)
  (find-pair (1721 979 366 299 675 1456))

  (->>
    (file->seq "resources/2020/day1/input-sample.txt")
    (map #(Integer/parseInt %))
    )
  (first test-sample)
  #_=> "1721"
  (Integer/parseInt (first test-sample))
  #_=> 1721)
(expect-entry "1721")
#_=> 299
(expect-entry (first test-sample))
#_=> 299
(def sample-set #{})
(def empty-set #{})
(check-entry-in-set sample-set 299 1721)
#_=> #{1721}
sample-set
(count test-sampl)
(def my-vector [1 2 3 4])
(filter #(= % 1) my-vector)
#_=> (1)
(filter #(= % 0) my-vector)
#_=> ()
(* 2 first (2 1))
(* 2 (first [1 2 3]))
(* 2 (first '(2 1)))


(if (sample-set 299)
  ((println "in set")
   (* 299 1))
  (conj sample-set 299))
(check-entry-in-set sample-set 299 1721)
(def test-set #{1})
(check-entry-in-set test-set 1 1721)
(conj #{} 1)

)