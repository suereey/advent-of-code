(ns advent-of-code.2020.day4)

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

(defn parse-key-value
  [a-string]
  (let [[key value] (clojure.string/split a-string #"\:")]
    {(keyword key) value}))

(defn parse-passport-s
  [passport-s]
  (->> (-> (clojure.string/join "\n" passport-s)
           (clojure.string/split #"[\r\n][\r\n]"))
       (mapv #(clojure.string/replace % "\n" " "))))

(defn passport->map
  [passport]
  (->> (clojure.string/split passport #" ")
       (mapv parse-key-value)
       (reduce merge {})))

(defn passport-s->map
  [passport-s]
  (mapv passport->map (parse-passport-s passport-s)))

(defn valid-passport?
  [map]
  (let [required-keys #{:ecl :pid :eyr :hcl :byr :iyr :hgt}
        passport-keys (set (keys map))
        valid? (= required-keys (clojure.set/intersection required-keys passport-keys))]
    valid?))

(defn solution-day4
  [input-s]
  (->> (mapv valid-passport? (passport-s->map input-s))
       (filter true?)
       count)
  )
;; Rich Comment Blocks
(comment
  (do (def input-s (file->seq "resources/2020/day4/input-sample.txt"))
      input-s)
  #_=> ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd"
        "byr:1937 iyr:2017 cid:147 hgt:183cm"
        ""
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884"
        "hcl:#cfa07d byr:1929"
        ""
        "hcl:#ae17e1 iyr:2013"
        "eyr:2024"
        "ecl:brn pid:760753108 byr:1931"
        "hgt:179cm"
        ""
        "hcl:#cfa07d eyr:2025 pid:166559648"
        "iyr:2011 ecl:brn hgt:59in"]


  (clojure.string/split "ecl:gry" #"\:")
  #_=> ["ecl" "gry"]

  (parse-key-value "ecl:gry")
  #_=> {:ecl "gry"}


  (clojure.string/join "\n" input-s)
  #_=>
  "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
   byr:1937 iyr:2017 cid:147 hgt:183cm

   iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
   hcl:#cfa07d byr:1929

   hcl:#ae17e1 iyr:2013
   eyr:2024
   ecl:brn pid:760753108 byr:1931
   hgt:179cm

   hcl:#cfa07d eyr:2025 pid:166559648
   iyr:2011 ecl:brn hgt:59in"

  (type (clojure.string/join "\n" input-s))
  #_=> java.lang.String

  (-> (clojure.string/join "\n" input-s)
      (clojure.string/split #"[\r\n][\r\n]"))
  #_=> ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"
        "hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"
        "hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"]


  (clojure.string/replace "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm" "\n" "")
  #_=> "ecl:gry pid:860033327 eyr:2020 hcl:#fffffdbyr:1937 iyr:2017 cid:147 hgt:183cm"

  (mapv #(clojure.string/replace % "\n" " ")
        ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"
         "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"
         "hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"
         "hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"])
  #_=> ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
        "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
        "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"]

  (parse-passport-s input-s)
  #_=> ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
        "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
        "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"]

  (do (def sample-string "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm")
      sample-string)
  #_=> "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"


  (clojure.string/split sample-string #" ")
  #_=> ["ecl:gry" "pid:860033327" "eyr:2020" "hcl:#fffffd" "byr:1937" "iyr:2017" "cid:147" "hgt:183cm"]

  (mapv parse-key-value ["ecl:gry" "pid:860033327" "eyr:2020" "hcl:#fffffd" "byr:1937" "iyr:2017" "cid:147" "hgt:183cm"])
  #_=> [{:ecl "gry"} {:pid "860033327"} {:eyr "2020"} {:hcl "#fffffd"} {:byr "1937"} {:iyr "2017"} {:cid "147"} {:hgt "183cm"}]


  (reduce merge {}
          [{:ecl "gry"} {:pid "860033327"} {:eyr "2020"} {:hcl "#fffffd"} {:byr "1937"} {:iyr "2017"} {:cid "147"} {:hgt "183cm"}])
  #_=> {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}

  (passport->map sample-string)
  #_=> {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}

  (mapv passport->map (parse-passport-s input-s))
  #_=> [{:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}
        {:iyr "2013", :ecl "amb", :cid "350", :eyr "2023", :pid "028048884", :hcl "#cfa07d", :byr "1929"}
        {:hcl "#ae17e1", :iyr "2013", :eyr "2024", :ecl "brn", :pid "760753108", :byr "1931", :hgt "179cm"}
        {:hcl "#cfa07d", :eyr "2025", :pid "166559648", :iyr "2011", :ecl "brn", :hgt "59in"}]

  (passport-s->map input-s)
  #_=> [{:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}
        {:iyr "2013", :ecl "amb", :cid "350", :eyr "2023", :pid "028048884", :hcl "#cfa07d", :byr "1929"}
        {:hcl "#ae17e1", :iyr "2013", :eyr "2024", :ecl "brn", :pid "760753108", :byr "1931", :hgt "179cm"}
        {:hcl "#cfa07d", :eyr "2025", :pid "166559648", :iyr "2011", :ecl "brn", :hgt "59in"}]

  (do (def sample-map {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"})
      sample-map)
  #_=> {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}

  (keys sample-map)
  #_=> (:ecl :pid :eyr :hcl :byr :iyr :cid :hgt)
  (set (keys sample-map))
  #_=> #{:cid :ecl :byr :iyr :hgt :pid :hcl :eyr}

  (valid-passport? sample-map)
  #_=> true

  (mapv valid-passport? [{:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}
                         {:iyr "2013", :ecl "amb", :cid "350", :eyr "2023", :pid "028048884", :hcl "#cfa07d", :byr "1929"}
                         {:hcl "#ae17e1", :iyr "2013", :eyr "2024", :ecl "brn", :pid "760753108", :byr "1931", :hgt "179cm"}
                         {:hcl "#cfa07d", :eyr "2025", :pid "166559648", :iyr "2011", :ecl "brn", :hgt "59in"}])
  #_=> [true false true false]

  (mapv valid-passport? (passport-s->map input-s))
  #_=> [true false true false]

  (solution-day4 input-s)
  #_=> 2

  (do (def input (file->seq "resources/2020/day4/input.txt"))
      input)
  (solution-day4 input)
  #_=> 254
  )
