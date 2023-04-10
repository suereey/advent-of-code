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
  (mapv (fn [row] [(* row down) (* row right)]) (range total-row)))

;; input is location [1 3] output is if it is a tree -> true/false
(defn is-tree?
  [checked-location input-s]
  (let [row (checked-location 0)
        col (checked-location 1)
        location (nth (input-s row) col)]
    (= location \X)))

;; Rich Comment Block
(comment
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt")) input-s)
  #_=>
  ["..##.........##.........##.........##.........##.........##.......  --->"
   "#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.."
   "..."
   "..."]

  ;;solution for part 1
  (let [total-row (count input-s)]
    (->>
      (mapv #(is-tree? % input-s) (checked-location-s 3 1 11))
      (filter true?)
      (count)))
  #_=> 7

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
  (map (fn [x] [(+ x) (* 3 x)]) (range 11))
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30])


  (input-s 0)
  #_=> "..##.........##.........##.........##.........##.........##.......  --->"
  (nth (input-s 0) 2)
  #_=> \#
  (count input-s)
  #_=> 11

  (is-tree? [1 3] input-s)
  #_=> false
  (map (fn [x] [x (* 3 x)]) (range 4))
  #_=> ([0 0] [1 3] [2 6] [3 9])

  (mapv (fn [x] [(+ x 1) (* x 3)]) (range 11))
  #_=> [[1 0] [2 3] [3 6] [4 9] [5 12] [6 15] [7 18] [8 21] [9 24] [10 27] [11 30]]

  (checked-location-s 3 1 11)
  #_=> [[1 0] [2 3] [3 6] [4 9] [5 12] [6 15] [7 18] [8 21] [9 24] [10 27] [11 30]])


