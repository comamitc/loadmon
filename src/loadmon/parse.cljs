(ns loadmon.parse
  (:require [loadmon.reader.gpx :refer [load-file]]))

(def ^:private load-fns {:gpx load-file})
(def ^:private norm-fns {:rtss #()})

(defn parse
  "Parses a fitness stream returning the TSS of a workout. Currently, in-format
  must be :gpx and strategy must be :rtss."
  [in-fmt strategy uri]
  (let [load-fn (or (get load-fns in-fmt)
                    (throw (js/Error. (str "Invalid input format: "
                                           (name in-fmt)))))]
    ; returns a channel
    (load-fn uri)))
