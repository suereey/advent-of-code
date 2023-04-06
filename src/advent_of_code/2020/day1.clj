(ns advent-of-code.2020.day1)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

(defn intify
  [expense-report]
  (map #(Integer/parseInt %) expense-report))

(defn expect-expenses-s
  [intify-expenses]
  (let [expenses        intify-expenses
        expect-expenses (map #(- 2020 %) expenses)]
    expect-expenses))

(defn filter-expenses-pair
  [intify-expenses expect-expenses-s]
  (filter #(contains? (set intify-expenses) %) expect-expenses-s))


;; Rich Comment Block
(comment
  ;; read the file into a sequence
  (do (def input-sample (file->seq "resources/2020/day1/input-sample.txt"))
      input-sample)
  #_=> ["1721" "979" "366" "299" "675" "1456"]


  (let [intify-expense    (intify input-sample)
        expect-expenses-s (expect-expenses-s intify-expense)
        res-list          (filter-expenses-pair intify-expense expect-expenses-s)
        ]
    (reduce * res-list))
  #_=> 514579

  ;; practice filter sample2, keep the value which exsist in sample 1
  (def sample1 [1721 979 366 299 675 1456])
  (def sample2 (list 299 1041 1654 1721 1345 564))
  (filter #(contains? (set sample1) %) sample2)

  ;; practice intify sample
  (map #(Integer/parseInt %) input-sample)
  #_=> (1721 979 366 299 675 1456)

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
  )
