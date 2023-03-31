(ns advent-of-code.2022.day6-practice)

; key method to use:
(first (partition 4 1 "mjqjpqmgbljsphdztnvjfqwrcgsmlb"))

(defn file->seq
  "read a file as a seq (per line)"
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

(defn four-letter-seq
  "take a string, split into 4 letters' string with 1 letter step"
  [each-string]
  (-> (partition 4 1 each-string)))

(defn map-every-string
  "take every string in the vector,
  do 4-letter-seq method for each string in list"
  [input-samples]
  (map four-letter-seq input-samples))

(defn unique-four-letter
  "take the 4 letter list to check if it's unique"
  [four-letter]
  (= (count four-letter)
     (count (set four-letter))))

(defn string-list-to-4letter-list
  "take the string list into 4 letter sublist
  index the 4 letter sublist and see if this sublist is unique"
  [letter-seq]
  (doseq [n letter-seq]
    ;(println "this is" n)
    (doseq [[index four-letter] (map-indexed vector n)]
      (println "Sublist at index" index ":" four-letter ":" (unique-four-letter four-letter)))))



;; Rich Comment Block
(comment

  ;; Numerous examples
  (do (def input-samples (file->seq "resources/2022/day6/input-sample.txt"))
      input-samples)
  #_=> ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        "bvwbjplbgvbhsrlpgdmjqwftvncz"
        "nppdvjthqldpwncqszvftbrmjlhg"
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"]
  (def first-string (first input-samples))
  (four-letter-seq first-string)

  (map-every-string input-samples)
  #_=> (
        ((\a \b \c \d) () () ...)
        ()
        ...
        )

  ; test with a shorter input. function work
  (def input-test ["abcdef" "ghijk" "xyzab"])
  (map-every-string input-test)
  #_=> (((\a \b \c \d) (\b \c \d \e) (\c \d \e \f))
      ((\g \h \i \j) (\h \i \j \k))
      ((\x \y \z \a) (\y \z \a \b)))
  )
;(map-every-string input-samples)
(string-list-to-4letter-list (map-every-string input-test))

(string-list-to-4letter-list (map-every-string input-samples))

; i can get
; Sublist at index 0 : (a b c d) : true
; result will be this index + 4


  (count '(\a \b \c \d))
  (unique-four-letter '(\a \a \c \d))
(type (map unique-four-letter (map-every-string input-test)))
(map unique-four-letter (map-every-string input-test))










