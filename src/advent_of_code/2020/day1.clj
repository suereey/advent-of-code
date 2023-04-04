(ns advent-of-code.2020.day1)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

; iterate each value, 2020- val in set -> return
;                     2020 - val not in set -> add val to set

(defn expect-numeric-entry
  [cur-entry]
  (- 2020 cur-entry))

(defn expect-entry
  [cur-entry]
  (->> cur-entry
       (Integer/parseInt)
       (- 2020)))
(defn check-entry-in-set
  [cur-set expect-entry cur-entry]
  (if (cur-set expect-entry)
    (* expect-entry cur-entry)
    (conj cur-set cur-entry)))



;; Rich Comment Block
(comment
  ;; read the file into a sequence

  (->>
    (let input-sample (file->seq "resources/2020/day1/input-sample.txt"))
    )
  (file->seq "resources/2020/day1/input-sample.txt")
  (def test-sample ["1721" "979" "366" "299" "675" "1456"])
  (def sample-set #{})
  (first test-sample)
  #_=> "1721"
  (Integer/parseInt (first test-sample))
  #_=> 1721
  (expect-entry (Integer/parseInt (first test-sample)))
  #_=> 299

  (expect-entry "1721")
  (if (sample-set 299)
    ((println "in set")
     (* 299 1))
    (conj sample-set 299))
  (check-entry-in-set sample-set 299 1721)
  (def test-set #{1})
  (check-entry-in-set test-set 1 1721)
  (conj #{} 1)

  )