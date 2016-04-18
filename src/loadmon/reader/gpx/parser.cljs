(ns loadmon.reader.gpx.parser
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [chan put! close! <! >!]]
            [tubax.core :refer [xml->clj]]
            [tubax.helpers :as th]
            [cljs-time.coerce :refer [to-long]]
            [loadmon.calc.distance :refer [distance]]
            [loadmon.calc.common :refer [pace]]
            [loadmon.calc.ngp :refer [calc-ngp]]))

(def ^:private fs (nodejs/require "fs"))

(def ^:private file-ch (chan 1))
(def ^:private out-ch (chan 1))

;;==============================================================================
;; DATASTRUCTURE
;;==============================================================================
(defn- get-time [n]
  (-> n
      (th/find-first {:tag :time})
      (th/text)
      (to-long)))

(defn- reduce-intervals [acc curr]
  (let [ttl-time (:ttl-time acc)
        ttl-dist (:ttl-dist acc)
        last     (:last acc)
        last?    (some? last)
        elapsed  (if last? (- (get-time curr) (get-time last)) 0)
        lat-lon  (:attributes curr)
        dist     (if last? (distance (:attributes last) lat-lon) 0)
        interval (calc-ngp
                   last
                   curr
                   (merge
                     lat-lon
                     {:dist    dist,
                      :elapsed elapsed,
                      :time    (get-time curr),
                      :pace    (pace dist elapsed)}))
        new-acc (assoc
                  acc
                  :last curr
                  :ttl-dist (if (some? ttl-dist) (+ ttl-dist dist) 0)
                  :ttl-time (if (some? ttl-time) (+ ttl-time elapsed) elapsed))]
    (update-in new-acc [:intervals] conj interval)))


(defn- make-tracks [data]
  (-> data
      (th/find-first {:tag :trkseg})
      (->> th/children
           (reduce reduce-intervals {}))
      (dissoc :last)))

;; park and wait for a file to be read
(go
  (let [xml-data (<! file-ch)
        clj-data (xml->clj (str (:data xml-data)))]
    (put! out-ch (make-tracks clj-data) #(close! out-ch))))

(defn- callback [err data]
  (when (some? err) (throw (js/Error. err)))
  (put! file-ch {:data data} #(close! file-ch)))

(defn parse-file
  "Given a .gpx filename, open and parse the file into a usable datastructure."
  [file-name]
  (.readFile fs file-name callback)
  out-ch)
