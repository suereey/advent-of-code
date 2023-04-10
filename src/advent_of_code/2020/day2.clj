(ns advent-of-code.2020.day2)

(defn file->seq
  [filename]
  (->> (-> filename
           (slurp)
           (clojure.string/split #"\n"))
       (mapv #(clojure.string/split % #" "))))

;; for solution 2
(defn char
  [password-policy]
  (first (password-policy 1)))

(defn password
  [password-policy]
  (password-policy 2))

(defn char-freq
  [char password]
  (count (filter #(= char %) password)))

(defn policy
  [password-policy]
  (let [policy-freq-string (password-policy 0)
        policy-freq-split  (clojure.string/split policy-freq-string #"-")
        policy-freq-v      (mapv #(Integer/parseInt %) policy-freq-split)]
    policy-freq-v))

;; for part 1
(defn actual-freq
  [password-policy]
  (let [char        (char password-policy)
        password    (password password-policy)
        actual-freq (char-freq char password)]
    actual-freq))

(defn compare-freq
  [policy-freq actual-freq]
  (and (>= actual-freq (policy-freq 0)) (<= actual-freq (policy-freq 1))))

;; for part 2
(defn logic-xor
  [boolean1 boolean2]
  (if (not (and boolean1 boolean2))
    (or boolean1 boolean2)
    false))

(defn char-index
  [char policy password]
  (let [char1    (nth password (- (policy 0) 1))
        char2    (nth password (- (policy 1) 1))
        boolean1 (= char1 char)
        boolean2 (= char2 char)]
    (logic-xor boolean1 boolean2)))

;; Rich Comment Block
(comment
  (do (def input-s (file->seq "resources/2020/day2/input-sample.txt")) input-s)
  ;; update the file->seq function. not return this ["1-3 a: abcde" "1-3 b: cdefg" "2-9 c: ccccccccc"]
  #_=> [["1-3" "a:" "abcde"] ["1-3" "b:" "cdefg"] ["2-9" "c:" "ccccccccc"]]

  ;; solution here-part 1
  (let [actual-freq  (mapv actual-freq input-s)
        policy       (mapv policy input-s)
        compare-freq (mapv compare-freq policy actual-freq)]
    (count (filter identity compare-freq)))
  #_=> 2

  ;; solution here-part 2
  (let [char       (mapv char input-s)
        password   (mapv password input-s)
        policy     (mapv policy input-s)
        char-index (mapv (fn [char password policy]
                           (char-index char policy password))
                         char password policy)]
    (count (filter identity char-index)))

  ;; question 1: why this one is not working
  (mapv (char-index
          [\a \b \c]
          ["abcde" "cdefg" "ccccccccc"]
          [[1 3] [1 3] [2 9]]))
  (mapv char-index [\a \b \c]
        ["abcde" "cdefg" "ccccccccc"]
        [[1 3] [1 3] [2 9]])

  (mapv (fn [char password policy]
          (char-index char policy password))
        [\a \b \c]
        ["abcde" "cdefg" "ccccccccc"]
        [[1 3] [1 3] [2 9]])
  #_=> [true false false]

  ;; question 2 xor logic in clojure?
  ;; write the xor logic based on problem requirement
  ;; a b
  ;; true false
  ;; false true
  ;; and -> false
  ;; or -> true
  (logic-xor true true)
  #_=> false
  (logic-xor true false)
  #_=> true
  (logic-xor false false)
  #_=> false
  (logic-xor false true)
  #_=> true

  ;; test the basic functions
  (char ["1-3" "a:" "abcde"])
  #_=> \a
  (password ["1-3" "a:" "abcde"])
  #_=> "abcde"
  (policy ["1-3" "a:" "abcde"])
  #_=> [1 3]
  (char-index \a [1 3] "abcde")
  #_=> true
  (char-index \c [2 9] "ccccccccc")
  #_=> false

  (mapv char input-s)
  #_=> [\a \b \c]
  (mapv policy input-s)
  #_=> [[1 3] [1 3] [2 9]]

  ; get char from string by index
  (nth "Abcd" 0)
  #_=> \A
  (char-index \a [1 3] "aacae")
  #_=> true

  ;; practice file -> seq, want to yield a list contains sublist
  (def string-input "1-3 a: abcde")
  (-> "1-3 a: abcde"
      (clojure.string/split #" "))
  #_=> ["1-3" "a:" "abcde"]

  (clojure.string/split string-input #" ")
  #_=> ["1-3" "a:" "abcde"]

  (mapv #(clojure.string/split % #" ") input-s)
  #_=> [["1-3" "a:" "abcde"] ["1-3" "b:" "cdefg"] ["2-9" "c:" "ccccccccc"]]

  ;; practice filter and char-freq function
  (filter #(= \a %) "abca")
  #_=> (\a \a)
  (count (filter #(= \a %) "abca"))
  #_=> 2
  (char-freq "a" "abca")
  #_=> 0
  (char-freq \a "abca")
  #_=> 2

  ;; check the frequency of the target char in a password
  (actual-freq ["1-3" "a:" "abcde"])
  #_=> 1
  (policy-freq ["1-3" "a:" "abcde"])
  #_=> [1 3]
  (compare-freq [1 3] 2)
  #_=> true

  (count (filter identity [true false true]))
  #_=> 2

  (Integer/parseInt "2")
  #_=> 2
  (vec (map #(Integer/parseInt %) ["1" "2"]))
  #_=> [1 2]

  (mapv #(Integer/parseInt %) ["1" "2"])
  #_=> [1 2])