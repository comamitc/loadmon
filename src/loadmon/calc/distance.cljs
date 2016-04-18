(ns loadmon.calc.distance
  (:require [goog.math :as math]))

(def ^:private radius-of-earth 6378.1)
(def ^:private js-math js/Math)

(defn- sin [n]
  (.sin js-math n))

(defn- cos [n]
  (.cos js-math n))

(defn- sqrt [n]
  (.sqrt js-math n))

(defn- asin [n]
  (.asin js-math n))

(defn- atan2 [n]
  (.atan2 js-math n))

(defn- to-rad [n]
  (math/toRadians n))

(defn distance [start end]
  "return the distance of two points in kilometers"
  (let [{lat1 :lat lon1 :lon} start
        {lat2 :lat lon2 :lon} end
        dlat (to-rad (- lat2 lat1))
        dlon (to-rad (- lon2 lon1))
        a    (+ (* (sin (/ dlat 2)) (sin (/ dlat 2))) (* (sin (/ dlon 2)) (sin (/ dlon 2)) (cos (to-rad lat1)) (cos (to-rad lat2))))]
    (* radius-of-earth 2 (asin (sqrt a)))))
