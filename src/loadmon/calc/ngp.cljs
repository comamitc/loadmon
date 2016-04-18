(ns loadmon.calc.ngp
  (:require [loadmon.calc.common :refer [atan to-deg]]
            [tubax.helpers :as th]))

(defn- get-ele [n]
  (-> n
      (th/find-first {:tag :ele})
      (th/text)))

(defn- grade [start end dist]
  (let [ele-chg (- end start)
        rad     (atan (/ ele-chg (* dist 1000)))]
    (to-deg rad)))

(defn- decline-pace [pace grade]
  (let [coef 0.01815]
    (/ pace (+ 1 (* coef grade)))))

(defn- incline-pace [pace grade]
  (let [coef 0.033]
    (/ pace (+ 1 (* coef grade)))))

(defn calc-ngp
  "Calculate the Normalized Graded Pace of two intervals."
  [last curr interval]
  (let [s-ele (get-ele last)
        e-ele (get-ele curr)
        p (:pace interval)
        g (grade s-ele e-ele (:dist interval))]
    (assoc interval :ngp (if (> g 0) (incline-pace p g) (decline-pace p g)))))
