(ns loadmon.calc.tss
  (:require [loadmon.calc.common :refer [sq]]))

(defn- avg-gap [intervals]
  (let [sum (reduce #(+ %1 (:ngp %2)) 0 intervals)]
    (/ sum (count intervals))))

(defn- *if* [ngp ftp]
  (/ ftp ngp))

(defn r-tss [data ftp]
  (let [ngp  (avg-gap (:intervals data))
        secs (/ (:ttl-time data) 1000)
        intf (*if* ngp ftp)]
        ; x    (* (/ 60 (* ftp 10)) 100)]
    (* (sq intf) 100 (/ secs 3600))))

; (defn- avg-pace [intervals]
;   (let [sum (reduce #(+ %1 (:pace %2)) 0 intervals)]
;     (/ sum (count intervals))))
;
; (defn r-tss2 [data ftp]
;   (let [ngp  (avg-gap (:intervals data))
;         ap   (avg-pace (:intervals data))
;         secs (/ (:ttl-time data) 1000)
;         n    (* secs ngp (*if* ngp ftp) 100)
;         d    (* ftp 3600)
;         frac (/ n d)]
;     (print (str "mins: " (/ secs 60)))
;     (print (str "ngp: " ngp))
;     (print (str "ftp: " ftp))
;     (print (str "if : " (*if* ngp ftp)))
;     frac))
