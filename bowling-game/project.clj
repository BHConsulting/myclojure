(defproject bowling-game "0.1.0-SNAPSHOT"
  :description "Bowling game in Clojure"
  :url "https://github.com/BHConsulting/myclojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot bowling-game.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
