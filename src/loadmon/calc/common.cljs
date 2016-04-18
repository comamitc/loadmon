(ns loadmon.calc.common
  (:require [goog.math :as math]))

(def ^:private js-math js/Math)

(defn sin [n]
  (.sin js-math n))

(defn cos [n]
  (.cos js-math n))

(defn sqrt [n]
  (.sqrt js-math n))

(defn asin [n]
  (.asin js-math n))

(defn atan2 [n]
  (.atan2 js-math n))

(defn to-rad [n]
  (math/toRadians n))

(defn atan [n]
  (.atan js-math n))

(defn to-deg [n]
  (math/toDegrees n))

(defn km->mi [n]
  (* n 0.621371))

(defn pace [km ms]
  (let [min (/ ms 1000 60)]
    (/ min km)))
