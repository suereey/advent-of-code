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

  ; key method to use:
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
  (idx-unique-char "bvwbjplbgvbhsrlpgdmjqwftvncz"))










