(ns advent-of-code.2022.day6-practice)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

(defn packet-window-s
  [window-size s]
  (partition window-size 1 s))

(defn unique-chars?
  [char-s]
  (= (count char-s)
     (count (set char-s))))

(defn idx-unique-char
  [sample-signal]
  (->> sample-signal
       (packet-window-s 4)
       (map (fn [idx chars]
              {:idx    idx
               :window chars
               :uniq? (unique-chars? chars)})
            (range))
       (filter :uniq?)
       (first)
       (:idx)
       (+ 4)
       )
  )


;; Rich Comment Block
(comment
  ;; read file
  (file->seq "resources/2022/day6/input-sample.txt")
  #_=> ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        "bvwbjplbgvbhsrlpgdmjqwftvncz"
        "nppdvjthqldpwncqszvftbrmjlhg"
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"]
  ;; test method for each string
  (idx-unique-char "bvwbjplbgvbhsrlpgdmjqwftvncz")
  #_=> 5
  ;; use this method on sequences using map
  (map idx-unique-char ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"
                        "bvwbjplbgvbhsrlpgdmjqwftvncz"
                        "nppdvjthqldpwncqszvftbrmjlhg"
                        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
                        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"])
  #_=> (7 5 6 10 11)
  ;; implement this function
  (->>
    (file->seq "resources/2022/day6/input-sample.txt")
    (map idx-unique-char)
    )

  ;; Note and Testing
  ;; key method to use:
  (first (partition 4 1 "mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
  #_=> (\m \j \q \j)
  ;; Numerous examples
  (do (def sample-signals (file->seq "resources/2022/day6/input-sample.txt"))
      (def sample-signal (first sample-signals))
      sample-signals)
  #_=> ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        "bvwbjplbgvbhsrlpgdmjqwftvncz"
        "nppdvjthqldpwncqszvftbrmjlhg"
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"]

  (->> (packet-window-s 4 sample-signal)
       (take 4))
  #_=> '((\m \j \q \j) (\j \q \j \p) (\q \j \p \q) (\j \p \q \m))

  (->> sample-signal
       (packet-window-s 4)
       #_(map unique-chars?)
       (filter unique-chars?)
       )

  (->> sample-signal
       (packet-window-s 4)
       (map (fn [idx chars]
              {:idx    idx
               :window chars
               :uniq? (unique-chars? chars)})
            (range))
       (filter :uniq?)
       (first)
       (:idx)
       (+ 4)
       )
  ; call final method:
  (idx-unique-char "bvwbjplbgvbhsrlpgdmjqwftvncz")

  ; implement the partition function by loop
  ; sample looping
  (loop [i 10]
    (when (> i 1)
      (println (str "Hi! " i))
      (recur (- i 2))))
  ;; implement loop for parition method
  (defn generate-partition-function
    [window-size s]
    (loop [i 0
           curr-s s
           res []]
      (if (< i (count s))
        (let [new-partition (take window-size curr-s)]
          (if (= (count new-partition) window-size)
            (let [new-res (conj res new-partition)
                  new-curr-s (rest curr-s)
                  new-i (+ i 1)]
              (recur new-i new-curr-s new-res))
            res))
        res)))

  (generate-partition-function  4 "abcdefg")
  #_=> [(\a \b \c \d)
        (\b \c \d \e)
        (\c \d \e \f)
        (\d \e \f \g)]

  (take 2 "abcd")
  (rest "Abcd")
  (take 2 '(\b \c \d))
  )










