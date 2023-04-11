(ns advent-of-code.2020.day3)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

;; input: input-s ["...." "...."]
;; output is a list that indicate all locations that being checked in -> [[0 0] [1 3] [] ...]
(defn checked-location-s
  [right down total-row]
  (mapv (fn [row] [(* row down) (* row right)]) (range (/ total-row down))))

;; input is location [1 3] output is if it is a tree -> true/false
;; make sure it's tolerant to index out of range
(defn is-tree?
  [checked-location input-s]
  (let [row      (checked-location 0)
        col      (rem (checked-location 1) (count (input-s row)))
        location (nth (input-s row) col)]
    (or (= location \X) (= location \#))))

;; Rich Comment Block
(comment
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt")) input-s)
  #_=>
  ["..##.........##.........##.........##.........##.........##.......  --->"
   "#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.."
   "..."
   "..."]

  ;;solution for part 1 sample input
  (let [total-row (count input-s)]
    (->>
      (mapv #(is-tree? % input-s) (checked-location-s 3 1 total-row))
      (filter true?)
      (count)))
  #_=> 7

  ;; solution for part 1 real input
  (do (def input-s (file->seq "resources/2020/day3/input.txt")) input-s)

  (let [total-row (count input-s)]
    (->>
      (mapv #(is-tree? % input-s) (checked-location-s 3 1 total-row))
      (filter true?)
      (count)))
  #_=> 167

  ;; solution for part 2 sample input
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt")) input-s)

  ;; solution for part 2 real input
  (let [total-row (count input-s)]
    (->>
      (mapv #(is-tree? % input-s) (checked-location-s 1 1 total-row))
      (filter true?)
      (count)))
  #_=> 2

  (defn tree-num
    [right down input-s]
    (let [total-row (count input-s)]
      (->>
        (mapv #(is-tree? % input-s) (checked-location-s right down total-row))
        (filter true?)
        (count))))
  (* (tree-num 1 1 input-s)
     (tree-num 3 1 input-s)
     (tree-num 5 1 input-s)
     (tree-num 7 1 input-s)
     (tree-num 1 2 input-s))

  (tree-num 1 1 input-s)
  (tree-num 3 1 input-s)
  (tree-num 5 1 input-s)
  (tree-num 7 1 input-s)
  (tree-num 1 2 input-s)
  (checked-location-s 1 2 (count input-s))
  (count input-s)

  (do (def input-s (file->seq "resources/2020/day3/input.txt")) input-s)

  (let [total-row (count input-s)]
    (->>
      (mapv #(is-tree? % input-s) (checked-location-s 1 1 total-row))
      (filter true?)
      (count)))
  #_=> 167
  (let [total-row (count input-s)]
    total-row)
  #_=> 323
  (count input-s)
  #_=> 323
  (count (input-s 0))
  #_=>31

  ;; remain
  (rem 10 2)
  #_=> 0
  (rem 11 2)
  #_=>1
  (rem 1 2)
  #_=>1
  (rem 936 31)
  #_=> 6
  (count (input-s 312))
  #_=> 31

  (is-tree? [312 936] input-s)
  #_=> 323
  (checked-location-s 3 1 323)

  (count (filter true? [false false true false true true false true true true true]))
  #_=> 7

  (mapv #(is-tree? % input-s) (checked-location-s 3 1 11))
  #_=> [false false true false true true false true true true true]

  (nth (input-s 2) 6)
  #_=> \X
  (is-tree? [2 6] input-s)
  #_=> true
  (checked-location-s 3 1 11)
  #_=> [[0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30]]
  (map (fn [x] [x (* 3 x)]) (range 11))
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30])


  (is-tree? [1 3] input-s)
  #_=> false
  (map (fn [x] [x (* 3 x)]) (range 4))
  #_=> ([0 0] [1 3] [2 6] [3 9])

  (mapv (fn [x] [(+ x 1) (* x 3)]) (range 11))
  #_=> [[1 0] [2 3] [3 6] [4 9] [5 12] [6 15] [7 18] [8 21] [9 24] [10 27] [11 30]]

  (checked-location-s 3 1 11)
  #_=> [[1 0] [2 3] [3 6] [4 9] [5 12] [6 15] [7 18] [8 21] [9 24] [10 27] [11 30]])
