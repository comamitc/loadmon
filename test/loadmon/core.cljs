(ns loadmon.test.core
  (:require [cljs.nodejs :as nodejs]
            [loadmon.core :refer [tss]]))

(nodejs/enable-util-print!)

(def fs (nodejs/require "fs"))

(defn- callback
  [err data]
  (when (some? err) (throw (js/Error. err)))
  (let [results (tss :gpx (str data) :rtss 5.19)]
    (print results)))

(defn -test []
  (.readFile fs "resources/examples/example-001.gpx" callback))

(set! *main-cli-fn* -test)
