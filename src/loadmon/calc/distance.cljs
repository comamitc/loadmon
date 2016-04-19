(ns loadmon.calc.distance
  (:require [loadmon.calc.common :refer [to-rad sin cos atan2 sqrt]]))

(def ^:private radius-of-earth 6378.1)

(defn distance [start end]
  "Use haversine's distance forumla to return the distance of two points in kilometers."
  (let [{lat1 :lat, lon1 :lon} start
        {lat2 :lat, lon2 :lon} end
        dlat (to-rad (- lat2 lat1))
        dlon (to-rad (- lon2 lon1))
        a (+
            (* (sin (/ dlat 2)) (sin (/ dlat 2)))
            (*
              (sin (/ dlon 2))
              (sin (/ dlon 2))
              (cos (to-rad lat1))
              (cos (to-rad lat2))))]
    (* radius-of-earth 2 (atan2 (sqrt a) (sqrt (- 1 a))))))
