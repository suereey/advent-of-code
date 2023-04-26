(ns advent-of-code.2020.day3
  (require '[clojure.math.numeric-tower :as math]))

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

;; This is the location (regardless of the grid) that will be checked based on the [right down]
;; for example, if right = 2 down = 1; it will return a lazy sequence [[0 0] [1 3] [2 6] ...]
(defn slope-location-s
  [[right down]]
  (map (fn [row]
         [(* row down) (* row right)])
       (iterate inc 0)))

;; The lazy seq was generated in the above function
;; the filter-location-s function take the seq based on grid's total row
(defn filter-location-s
  [[right down] count-grid-row]
  (take (Math/ceil (/ count-grid-row down)) (slope-location-s [right down])))


(defn location-char
  [grid location]
  (let [row              (location 0)
        col              (location 1)
        repeated-pattern (cycle (grid row))
        location-char    (nth repeated-pattern col)]
    location-char))

(defn tree?
  [char]
  (or (= char \X)
      (= char \#)))

(defn count-tree
  [grid [right down]]
  (let [location-s      (filter-location-s [right down] (count grid))
        location-char-s (mapv #(location-char grid %) location-s)]
    (->> location-char-s
         (mapv tree?)
         (filter identity)
         count)))


;; Rich Comment Block
(comment
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt"))
      input-s)
  #_=>
  ["..##.........##.........##.........##.........##.........##.......  --->"
   "#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#.."
   "..."
   "..."]

  (take 5 (slope-location-s [3 1]))
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12])

  (filter-location-s [3 1] 5)
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12])

  (location-char input-s [0 0])
  #_=> \.

  (location-char input-s [0 2])
  #_=> \#

  (mapv #(location-char input-s %) '([0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30]))
  #_=> [\. \O \X \O \X \X \O \X \X \X \X]

  [(tree? \X) (tree? \#) (tree? \.) (tree? \A)]
  #_=> [true true false false]

  (mapv tree? [\. \O \X \O \X \X \O \X \X \X \X])
  #_=>  [false false true false true true false true true true true]

  (count (filter identity [false false true false true true false true true true true]))
  #_=> 7

  (let [location-s      (filter-location-s [3 1] (count input-s))
        location-char-s (mapv #(location-char input-s %) location-s)]
    (->> location-char-s
         (mapv tree?)
         (filter identity)
         count))
  #_=> 7


  ;; solution for part 1 real input
  (do (def input-part1 (file->seq "resources/2020/day3/input.txt"))
      input-part1)

  (let [location-s      (filter-location-s [3 1] (count input-part1))
        location-char-s (mapv #(location-char input-part1 %) location-s)]
    (->> location-char-s
         (mapv tree?)
         (filter identity)
         count))
  #_=> 167

  ;; solution for part 2 sample input
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt"))
      input-s)

  (count-tree input-s [3 1])
  #_=> 7
  (count-tree input-s [1 1])
  #_=> 2
  (count-tree input-s [5 1])
  #_=> 3
  (count-tree input-s [7 1])
  #_=> 4
  (count-tree input-s [1 2])
  #_=> 2

  (let [val1 (count-tree input-s [3 1])
        val2 (count-tree input-s [1 1])
        val3 (count-tree input-s [5 1])
        val4 (count-tree input-s [7 1])
        val5 (count-tree input-s [1 2])]
    (* val1 val2 val3 val4 val5))
  #_=> 336

  ;; solution for part 2 real input
  (do (def input-part2 (file->seq "resources/2020/day3/input.txt"))
      input-part2)
  (let [val1 (count-tree input-part2 [3 1])
        val2 (count-tree input-part2 [1 1])
        val3 (count-tree input-part2 [5 1])
        val4 (count-tree input-part2 [7 1])
        val5 (count-tree input-part2 [1 2])]
    (* val1 val2 val3 val4 val5))
  #_=> 736527114

  (count-tree input-part2 [3 1])
  #_=> 167
  (count-tree input-part2 [1 1])
  #_=> 53
  (count-tree input-part2 [5 1])
  #_=> 54
  (count-tree input-part2 [7 1])
  #_=> 67
  (count-tree input-part2 [1 2])
  #_=> 23

  (Math/ceil 2.3)
  #_=> 3.0
  )
