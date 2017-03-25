(defproject trademe-property-info "0.1.0-SNAPSHOT"
  :description "Fetch property information from trademe insight."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [cheshire "5.7.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [com.taoensso/timbre "4.8.0"]]
  :main ^:skip-aot trademe-property-info.core
  :target-path "target/%s"
  :plugins [[lein-cljfmt "0.5.6"]]
  :profiles {:uberjar {:aot :all}})
