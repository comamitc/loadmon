(ns loadmon.calc.tss)

(defn- avg-gap [intervals]
  (let [sum (reduce #(+ %1 (:ngp %2)) 0 intervals)]
    (/ sum (count intervals))))

(defn- *if* [ngp ftp]
  (/ ngp ftp))

(defn r-tss [data ftp]
  (let [ngp  (avg-gap (:intervals data))
        secs (/ (:ttl-time data) 1000)
        num  (* secs ngp (*if* ngp ftp))
        den  (* ftp 3600)]
    (* (/ num den) 100)))
