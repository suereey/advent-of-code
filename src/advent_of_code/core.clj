(ns advent-of-code.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Question 1
(def my-map {:a "A" :b "B" :c 3 :d 4})

; this example works using :or
(let [{a :a
       x :x, :or {x "Not found!"}
       :as all} my-map])

; cannot use 2 :or ?
(let [{a :a
       b :b
       c :c, :or {c "C"},
       e :e, :or {e "E"}
       :as all
       } my-map]
  (println a b e))

; have to use this method
(let [{:keys [a b c e] :or {c "C", e "E"}} my-map])

(defn configure [val options]
  (let [{:keys [debug verbose] :or {debug false, verbose false}} options]
    (println "val =" val " debug =" debug " verbose =" verbose)))

(configure 12 {:debug true})

;; Question 2
;; clojure destructuring keywords argument