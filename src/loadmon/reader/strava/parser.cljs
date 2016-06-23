(ns loadmon.reader.strava.parser
  (:require [cljs.nodejs :as nodejs]))

(def ^:private strava (nodejs/require "strava-v3"))

(defn parse
  [data tss-fn ftp]
  ;; @TODO: not sure what the data will look like but this will "zip" it
  (let [data (map vector (:time data) (:dist data) (:ele data))]
    nil))
