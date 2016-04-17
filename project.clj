(defproject loadmon "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :min-lein-version "2.5.3"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]
            [lein-npm "0.6.2"]]

  :npm {:dependencies [[source-map-support "0.4.0"]
                       [ws "0.8.1"]
                       [gpx-parse "0.10.4"]]}

  :source-paths ["src"]

  :clean-targets ["loadmon.js"
                  "target"]

  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src" "test"]
                        :notify-command ["node" "target/dev/loadmon.js"]
                        :compiler {
                                   :main loadmon.core
                                   :output-to "target/dev/loadmon.js"
                                   :output-dir "target/dev"
                                   :target :nodejs
                                   :optimizations :none
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["src"]
                        :compiler {
                                   :output-to "loadmon.js"
                                   :output-dir "target/loadmon"
                                   :target :nodejs
                                   :optimizations :simple}}]})
