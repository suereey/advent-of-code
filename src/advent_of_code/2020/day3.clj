(ns advent-of-code.2020.day3)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

;; input-s ["...." "...."]
;; output-s locations can be checked -> [[0 0] [1 3] [] ...]
(defn checked-location-s
  [right down]
  (map (fn [row]
         [(* row down) (* row right)])
       (iterate inc 0)))

(defn filter-location-s
  [right down total-row]
  (take (int (/ total-row down)) (checked-location-s right down)))

(defn tree?
  [char]
  (or (= char \#)
      (= char \X)))

(defn location-char
  [input-s location]
  (let [row              (location 0)
        col              (location 1)
        repeated-pattern (cycle (input-s row))
        location-char    (nth repeated-pattern col)]
    location-char))

(defn num-tree-solution
  [input-s right down]
  (let [location-s      (filter-location-s right down (count input-s))
        location-char-s (mapv #(location-char input-s %) location-s)]
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

  (take 5 (checked-location-s 3 1))
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12])
  ;; question why map works but not map v?
  ;(defn checked-location-s [right down]
  ;  (mapv (fn [row]
  ;         [(* row down) (* row right)])
  ;       (iterate inc 0)))
  ;(take 5 (checked-location-s 3 1))


  (filter-location-s 3 1 5)
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12])

  (location-char input-s [0 0])
  #_=> \.

  (location-char input-s [0 2])
  #_=> \#

  (map tree? [\# \X \.])
  #_=> (true true false)

  (filter-location-s 3 1 (count input-s))
  #_=> ([0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30])

  (mapv #(location-char input-s %) '([0 0] [1 3] [2 6] [3 9] [4 12] [5 15] [6 18] [7 21] [8 24] [9 27] [10 30]))
  #_=> [\. \O \X \O \X \X \O \X \X \X \X]

  (mapv tree? [\. \O \X \O \X \X \O \X \X \X \X])
  #_=> [false false true false true true false true true true true]

  (count (filter identity [false false true false true true false true true true true]))
  #_=> 7

  (let [location-s      (filter-location-s 3 1 (count input-s))
        location-char-s (mapv #(location-char input-s %) location-s)]
    (->> location-char-s
         (mapv tree?)
         (filter identity)
         count))
  #_=> 7


  ;; solution for part 1 real input
  (do (def input-part1 (file->seq "resources/2020/day3/input.txt"))
      input-part1)

  (let [location-s      (filter-location-s 3 1 (count input-part1))
        location-char-s (mapv #(location-char input-part1 %) location-s)]
    (->> location-char-s
         (mapv tree?)
         (filter identity)
         count))
  #_=> 167

  ;; solution for part 2 sample input
  (do (def input-s (file->seq "resources/2020/day3/input-sample.txt"))
      input-s)

  (num-tree-solution input-s 3 1)
  #_=> 7
  (num-tree-solution input-s 1 1)
  #_=> 2
  (num-tree-solution input-s 5 1)
  #_=> 3
  (num-tree-solution input-s 7 1)
  #_=> 4
  (num-tree-solution input-s 1 2)
  #_=> 2

  (let [val1 (num-tree-solution input-s 3 1)
        val2 (num-tree-solution input-s 1 1)
        val3 (num-tree-solution input-s 5 1)
        val4 (num-tree-solution input-s 7 1)
        val5 (num-tree-solution input-s 1 2)]
    (* val1 val2 val3 val4 val5))
  #_=> 336

  ;; solution for part 2 real input
  (do (def input-part2 (file->seq "resources/2020/day3/input.txt"))
      input-part2)
  (let [val1 (num-tree-solution input-part2 3 1)
        val2 (num-tree-solution input-part2 1 1)
        val3 (num-tree-solution input-part2 5 1)
        val4 (num-tree-solution input-part2 7 1)
        val5 (num-tree-solution input-part2 1 2)]
    (* val1 val2 val3 val4 val5))
  #_=> 704504196

  (num-tree-solution input-part2 3 1)
  #_=> 167
  (num-tree-solution input-part2 1 1)
  #_=> 53
  (num-tree-solution input-part2 5 1)
  #_=> 54
  (num-tree-solution input-part2 7 1)
  #_=> 67
  (num-tree-solution input-part2 1 2)
  #_=> 22
  
)
