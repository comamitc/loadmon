(ns loadmon.core
  (:require-macros
    [cljs.core.async.macros :refer [go]])
  (:require [cljs.nodejs :as nodejs]
            [loadmon.parse :refer [parse]]
            [cljs.core.async :refer [<!]]))

(nodejs/enable-util-print!)

(defn foo
  [o]
  (loop [x o
         ks (.keys js/Object x)
         acc {}]
    (let [k (first ks)
          v (aget x k)]
      (vector (keyword k)
              v))))

(defn jso->clj [x]
  (reduce (fn [acc curr]
            (let [v (aget x curr)]
              (assoc acc (keyword curr) v)))
          {}
          (.keys js/Object x)))


(defn -test []
  (go
    (let [resp (<! (parse :gpx :rtss "resources/examples/example-001.gpx"))]
      (print (jso->clj (:data resp))))))

(set! *main-cli-fn* -test)
