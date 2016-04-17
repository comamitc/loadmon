(ns loadmon.core
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [loadmon.reader.gpx :as gpx]
            [cljs.core.async :refer [<!]]))

(nodejs/enable-util-print!)

(defn -test []
  (go
    (let [resp (<! (gpx/load-file "resources/examples/example-001.gpx"))]
      (print resp))))

(set! *main-cli-fn* -test)
