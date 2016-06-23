(ns loadmon.core
  (:require [loadmon.reader.gpx.parser :refer [parse]]
            [loadmon.calc.tss :refer [r-tss]]))

(def ^:private parse-fns {:gpx parse})
(def ^:private norm-fns  {:rtss r-tss})

;; (tss :gpx stream :rtss ftp)
(defn tss
  "Parses a fitness stream returning the TSS of a workout. Currently, in-format
  must be :gpx and strategy must be :rtss."
  [format data strategy ftp]
  (let [parser (or (get parse-fns format)
                   (throw (js/Error. (str "Invalid input format: "
                                          (name format)))))
        tss-fn (or (get norm-fns strategy)
                   (throw (js/Error. (str "Invalid strategy: "
                                          (name strategy)))))]

    (parser data tss-fn ftp)))
