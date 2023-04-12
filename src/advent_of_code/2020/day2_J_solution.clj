(ns advent-of-code.2020.day2_J-solution
  (:require [clojure.string :as string]))

(defn file->seq
  [filename]
  (->> (slurp filename)
       (string/split-lines)))

(do (def input-s (file->seq "resources/2020/day2/input-sample.txt"))
    input-s)
#_=> ["1-3 a: abcde" "1-3 b: cdefg" "2-9 c: ccccccccc"]

(defn parse
  [pwd-policy-str]
  (let [[policy pass] (string/split pwd-policy-str #":\s")
        [[lo hi] [letter]] (map #(string/split % #"-")
                                (string/split policy #" "))]

    {:lo     (Integer/parseInt lo)
     :hi     (Integer/parseInt hi)
     :letter (first letter)
     :pass   pass}))


(defn valid-freq-matched-pwd?
  [{:keys [lo hi letter pass] :as _pwd-policy}]
  (let [letter-cnt (->> pass
                        (filter #(= letter %))
                        count)]
    (<= lo letter-cnt hi)))


(defn either-or
  [bool1 bool2]
  (or (and bool1 (not bool2))
      (and bool2 (not bool1))))


(defn valid-pos-matched-pwd?
  [{:keys [lo hi letter pwd]}]
  (let [[pos1 pos2] (mapv #(nth pwd (dec %))
                          [lo hi])
        [pos1-match pos2-match] (mapv #(= letter %)
                                      [pos1 pos2])]
    (either-or pos1-match
               pos2-match)))

(defn file->parsed-list-s
  [filename]
  (->> (slurp filename)
       (string/split-lines)
       (map parse)))

;;
(defn count-valid-passwords
  [policy-fn filename]
  (->> (file->parsed-list-s filename)
       (filter policy-fn)
       (count)))

(comment
  (do (def sample-pwd-policy-s (->> (file->seq "resources/2020/day2/input-sample.txt")
                                    (map parse)))
      sample-pwd-policy-s)
  #_=> '({:lo 1, :hi 3, :letter \a, :pass "abcde"}
         {:lo 1, :hi 3, :letter \b, :pass "cdefg"}
         {:lo 2, :hi 9, :letter \c, :pass "ccccccccc"})


  (parse-list-s "1-3 a: abcde")
  #_=> '{:lo 1, :hi 3, :letter \a, :pass "abcde"}

  (file->parsed-list-s "resources/2020/day2/input-sample.txt")
  #_=> '({:lo 1, :hi 3, :letter \a, :pass "abcde"}
         {:lo 1, :hi 3, :letter \b, :pass "cdefg"}
         {:lo 2, :hi 9, :letter \c, :pass "ccccccccc"})


  (count (filter #(= \a %) "abcde"))
  #_=> 1

  (nth "abcde" 0)
  #_=> \a

  (valid-pos-matched-pwd? {:lo 2, :hi 9, :letter \c, :pass "ccccccccc"})
  #_=> true

  (valid-pos-matched-pwd? {:lo 2, :hi 9, :letter \c, :pass "ccccccccc"})
  #_=> false

  (->> (file->seq "resources/2020/day2/input-sample.txt")
       (map parse)
       (filter valid-freq-matched-pwd?)
       (count))

  (count-valid-passwords valid-password-policy-lo-hi-times-letter-appear "resources/2020/day2/input-sample.txt")
  #_=> 2

  (count-valid-passwords valid-password-policy-exactly-one-pos-match "resources/2020/day2/input-sample.txt")
  #_=> 1

  (count-valid-passwords valid-password-policy-lo-hi-times-letter-appear "resources/2020/day2/input.txt")
  #_=> 524

  (count-valid-passwords valid-password-policy-exactly-one-pos-match "resources/2020/day2/input.txt")
  #_=> 485


  ;;---Lei's practice below this line
  (do (def input-sample "1-3 a: abcde")
      input-sample)
  #_=> "1-3 a: abcde"

  (string/split input-sample #":\s")
  #_=> ["1-3 a" "abcde"]

  (let [[policy pass] (string/split input-sample #":\s")]
    [policy pass])
  #_=> ["1-3 a" "abcde"]

  (map #(string/split % #"-")
       (string/split "1-3 a" #" "))
  #_=> (["1" "3"] ["a"])

  (let [[[lo hi] [letter]] (map #(string/split % #"-")
                                (string/split "1-3 a" #" "))]
    [lo hi letter])
  #_=> ["1" "3" "a"]

  (type {:a 1 :b 2 :c 3})
  #_=> clojure.lang.PersistentArrayMap

  (parse "1-3 a: abcde")
  #_=> {:lo 1, :hi 3, :letter \a, :pass "abcde"}
  )