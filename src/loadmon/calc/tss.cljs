(ns loadmon.calc.tss)

(defn- avg-gap [intervals]
  (let [sum (reduce #(+ %1 %2) 0 intervals)]
    (/ sum (count intervals))))

(defn- if* [ngp ftp]
  (/ ngp ftp))

(defn r-tss [gpx-data ftp]
  nil)
