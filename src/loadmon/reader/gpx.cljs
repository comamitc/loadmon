(ns loadmon.reader.gpx
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.async :refer [chan put! close!]]))

(def parser (nodejs/require "gpx-parse"))

(defn- callback [c]
  (fn [err data]
    (put! c {:error err :data data})
    (close! c)))

(defn load-file [file]
  (let [c (chan)]
    (.parseGpxFromFile parser file (callback c))
    c))
