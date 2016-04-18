(ns loadmon.test.core
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [loadmon.core :refer [tss]]
            [cljs.core.async :refer [<!]]))

(nodejs/enable-util-print!)

(defn -test []
  (go
    (let [resp (<! (tss :gpx :rtss "resources/examples/example-001.gpx"))]
      ;nil)))
      (print resp))))

(set! *main-cli-fn* -test)
