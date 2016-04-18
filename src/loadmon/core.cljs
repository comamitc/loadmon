(ns loadmon.core
  (:require [loadmon.reader.gpx.parser :refer [parse-file]]
            [loadmon.calc.tss :refer [r-tss]]))

(def ^:private load-fns {:gpx parse-file})
(def ^:private norm-fns {:rtss r-tss})

(defn tss
  "Parses a fitness stream returning the TSS of a workout. Currently, in-format
  must be :gpx and strategy must be :rtss."
  [in-fmt strategy uri]
  (let [load-fn (or (get load-fns in-fmt)
                    (throw (js/Error. (str "Invalid input format: "
                                           (name in-fmt)))))
        norm-fn (or (get norm-fns strategy)
                    (throw (js/Error. (str "Invalid TSS strategy: " strategy))))]
    (load-fn uri)))

;; (tss :gpx stream :rtss)
