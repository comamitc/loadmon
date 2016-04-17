(ns loadmon.parse)

(def ^:private formats {:gpx loadmon.reader/parse})
(def ^:private strategies {:rtss #()})

(defn parse
  "Parses a fitness stream returning the TSS of a workout. Currently, in-format
  must be :gpx and strategy must be :rtss."
  [in-format strategy])
