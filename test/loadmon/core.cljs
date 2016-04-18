(ns loadmon.test.core
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [loadmon.core :refer [parse]]
            [cljs.core.async :refer [<!]]))

(nodejs/enable-util-print!)

(defn -test []
  (go
    (let [resp (<! (parse :gpx :rtss "resources/examples/example-001.gpx"))]
      (print resp))))

(set! *main-cli-fn* -test)
