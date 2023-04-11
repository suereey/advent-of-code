(ns advent-of-code.2020.day2)

(defn file->policy-password
  [filename]
  (->> (-> filename
           (slurp)
           (clojure.string/split #"\n"))
       (mapv #(clojure.string/split % #" "))
       (mapv (fn [[policy-bounds letter pwd]]
               {:policy-bounds policy-bounds
                :letter        letter
                :pwd           pwd}))))

(defn policy-password->password
  [policy-password]
  (:pwd policy-password))

(defn policy-password->char
  [policy-password]
  (->> (:letter policy-password)
       first))

(defn policy-password->policy-bounds
  [policy-password]
  (let [policy-string (:policy-bounds policy-password)]
    (->> (-> policy-string
             (clojure.string/split #"-"))
         (mapv read-string))))

(defn char-freq-count
  [char password]
  (->> password
       (filter #(= char %))
       count))

(defn password-char-freq-count
  [policy-password]
  (let [char (policy-password->char policy-password)
        password (policy-password->password policy-password)]
    (char-freq-count char password)))

(defn freq-compare
  [char-freq policy-bound]
  (let [lower-bound (policy-bound 0)
        upper-bound (policy-bound 1)]
    (and (<= char-freq upper-bound)
         (>= char-freq lower-bound))))


;; for part 2 about index
(defn either-or
  [boolean1 boolean2]
  (or (and boolean1 (not boolean2))
      (and boolean2 (not boolean1))))

;; same function as policy-password->policy-bounds
(defn policy-password->policy-index
  [policy-password]
  (let [policy-string (:policy-bounds policy-password)]
    (->> (-> policy-string
             (clojure.string/split #"-"))
         (mapv read-string))))

(defn password->char
  [password policy-index]
  (let [index-1 (- (policy-index 0) 1)
        index-2 (- (policy-index 1) 1)
        char1 (nth password index-1)
        char2 (nth password index-2)]
    [char1 char2]))

(defn char-compare
  [char char-s]
  (let [char-match-1 (= char (char-s 0))
        char-match-2 (= char (char-s 1))]
    (either-or char-match-1 char-match-2)))

;; Rich Comment Block
(comment

  (do (def input-s (file->policy-password "resources/2020/day2/input-sample.txt"))
      input-s)
  ;; update the file->seq function. not return this ["1-3 a: abcde" "1-3 b: cdefg" "2-9 c: ccccccccc"]
  #_=> [{:policy-bounds "1-3", :letter "a:", :pwd "abcde"}
        {:policy-bounds "1-3", :letter "b:", :pwd "cdefg"}
        {:policy-bounds "2-9", :letter "c:", :pwd "ccccccccc"}]

  (policy-password->password {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> "abcde"

  (policy-password->char {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> \a

  (:policy-bounds {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> "1-3"
  (clojure.string/split "1-3" #"-")
  #_=> ["1" "3"]
  (mapv read-string ["1" "3"])
  #_=> [1 3]
  (policy-password->policy-bounds {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> [1 3]
  (mapv policy-password->policy-bounds input-s)
  #_=> [[1 3] [1 3] [2 9]]

  (char-freq-count \a "abcd")
  #_=> 1

  (password-char-freq-count {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> 1

  (mapv password-char-freq-count input-s)
  #_=> [1 0 9]

  (freq-compare 1 [1 3])
  #_=> true

  (mapv freq-compare
        (mapv password-char-freq-count input-s)
        (mapv policy-password->policy-bounds input-s))
  #_=> [true false true]

  ;; final solution for part 1.
  (->> (mapv freq-compare
             (mapv password-char-freq-count input-s)
             (mapv policy-password->policy-bounds input-s))
       (filter identity)
       count)
  #_=> 2

  ;; solution for part 2
  (either-or true false)
  #_=> true
  (apply either-or [true false])
  #_=> true
  (mapv #(apply either-or %) [[true true]
                           [true false]
                           [false true]
                           [false false]])
  #_=> [false true true false]

  (policy-password->policy-index {:policy-bounds "1-3", :letter "a:", :pwd "abcde"})
  #_=> [1 3]

  (password->char "abcde" [1 3])
  #_=> [\a \c]

  (char-compare \a [\a \c])
  #_=> true

  (mapv password->char
    (mapv policy-password->password input-s)
    (mapv policy-password->policy-index input-s))
  #_=> [[\a \c] [\c \e] [\c \c]]

  (mapv policy-password->char input-s)
  #_=> [\a \b \c]

  (mapv char-compare [\a \b \c] [[\a \c] [\c \e] [\c \c]])
  #_=> [true false false]

  (->> (let [policy-char-s (mapv policy-password->char input-s)
             char-s (mapv password->char
                          (mapv policy-password->password input-s)
                          (mapv policy-password->policy-index input-s))]
         (mapv char-compare policy-char-s char-s))
       (filter identity)
       count)
  #_=> 1)