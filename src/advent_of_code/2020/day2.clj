(ns advent-of-code.2020.day2
  (:import (javax.xml.stream.events Comment)))

(defn file->seq
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))

(defn intify-s
  [expense-report]
  (map #(Integer/parseInt %) expense-report))

(defn window-s
  [window intify-expenses]
  (partition window 1 intify-expenses))
; question the function that create all the combination of a list
; with certain window?

(defn sum+window
  [window-s]
  (map #(reduce + %) window-s))

(defn indexed+window
  [sum-val num-list]
  {:sum    sum-val
   :window num-list})


(defn select-window
  [window-s target-val]
  (let [idxed+window          (map indexed+window (sum+window window-s) window-s)
        filtered-idxed-window (filter #(= (:sum %) target-val) idxed+window)
        result                (:window (first filtered-idxed-window))]
    (reduce * result))
  )

;; Rich Comment Block
(comment

  (do (def input-sample (file->seq "resources/2020/day2/input-sample.txt"))
      input-sample)
  #_=> ["1721" "979" "366" "299" "675" "1456"]

  (do (def int-list (intify-s input-sample))
      int-list)
  #_=> (1721 979 366 299 675 1456)

  (do (def num-s (window-s 3 int-list))
      num-s)
  #_=> ((1721 979 366) (979 366 299) (366 299 675) (299 675 1456))
  ; sum to 2020 979 366 675

  (map #(reduce + %) '((1721 979 366) (979 366 299) (366 299 675) (299 675 1456)))
  (sum+window '((1721 979 366) (979 366 299) (366 299 675) (299 675 1456)))
  (sum+window num-s)
  #_=> (3066 1644 1340 2430)

  (do (def idxed+window (map indexed+window (sum+window num-s) num-s))
      idxed+window)
  #_=>
  ({:sum 3066, :window (1721 979 366)}
   {:sum 1644, :window (979 366 299)}
   {:sum 1340, :window (366 299 675)}
   {:sum 2430, :window (299 675 1456)})

  (do (def filtered-idxed-window (filter #(= (:sum %) 3066) idxed+window))
      filtered-idxed-window)
  #_=> ({:sum 3066, :window (1721 979 366)})

  (select-window num-s 3066)

  )

