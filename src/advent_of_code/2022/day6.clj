(ns advent-of-code.2022.day6
  (:require [clojure.java.io :as io]))
(require '[clojure.pprint :refer [pprint]])


(defn file->seq
  "read a file as a seq (per line)"
  [filename]
  (-> filename
      (slurp)
      (clojure.string/split #"\n")))





; example: if I have each-word, a sample-word here. how to further process
(def sample-word "bvwbjplbgvbhsrlpgdmjqwftvncz")
; create a queue
; loop through each letter
; if not in queue -> add letter into queue
; else in queue
; -> add letter into queue
; -> pop queue from front until the same letter is pop out.
; if queue len is 4, return the index of that element in queue

;; Elements idx,


(defn loop-through-signal
  [signal]
  (if (empty? signal)
    signal
    (do #_(println signal)
      (loop-through-signal (rest signal)))))

(
(conj '(1 3 4 4 5 6) 7)
(pop '(1 2 3))
(first '(1 2 3))
(last '(1 2 3))
(rest '(1 2 3))

(nthrest [1 2 3 4 5 6] 4)

(drop 4 [1 2 3 4 5 6])
(cons '(4 5 6) 4)

(take 3 (range 20))


(let [queue-length 4
      signal-s     (seq sample-word)]

  (loop [rem-signal (drop 4 signal-s)
         queue      (take queue-length rem-signal)]
    (if (empty? rem-signal)
      rem-signal

      (do (println queue)
          (recur (rest rem-signal)
                 (conj queue
                       (first rem-signal)
                       ))))))


(loop [i 10]
  (when (> i 1)
    (println (str "Hi! " i))
    (recur (- i 2))))





; define a queue
(def my-queue clojure.lang.PersistentQueue/EMPTY)
(type my-queue)
; define add element into queue
(def add-to-queue conj)

(add-to-queue my-queue 4)

; pop queue until equal
(defn pop-until-equal
  [queue value]
  (if (empty? queue)
    nil
    (let [elem (peek queue)]
      (if (= elem value)
        queue
        (recur (pop queue) value)))))
; define a counter to return the result
(def count-res 1)
; how to loop through the word and operate
; i need to add a termination
(doseq [each-letter sample-word]
  (println "this letter is " each-letter)
  (def count-res (+ count-res 1))

  (println "count result: " count-res)
  (if (some #(= each-letter %) my-queue)
    (do
      (println each-letter "in my queue")
      (def my-queue (update-queue my-queue each-letter))
      (def my-queue (pop-until-equal my-queue each-letter))
      (println "finish in queue execution")
      )
    (do
      (println each-letter "not in my queue")
      (def my-queue (update-queue my-queue each-letter))
      (println "finish not in queue execution"))
    )
  )

; 0329 After the study group

; 01 true vs truthy
; "truthy" refers to any value that is not false or nil
; lei question: what function return truthy instead of true? Why we need truthy

; defn function first-marker
; def first-uniq4-marker
; defn marker-location

(def first-uniq4-marker ())







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
input-samples
(first (input-samples))








;;













