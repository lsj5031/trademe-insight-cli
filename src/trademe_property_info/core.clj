(ns trademe-property-info.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [org.httpkit.client :as http]
            [taoensso.timbre :as timbre]
            [cheshire.core :refer [encode decode]])
  (:gen-class))

(defn get-place-id [address api-key]
  (timbre/info "Querying place id from google by address " address)
  (let [resp (http/get "https://maps.googleapis.com/maps/api/place/autocomplete/json" {:query-params {:key api-key :input address}})]
    (when-not (:error @resp)
      (get-in (decode (:body @resp)) ["predictions" 0 "place_id"]))))

(defn get-property-data-guid [place-id]
  (timbre/info "Querying data guid from trademe " place-id)
  (let [resp (http/get "https://www.trademe.co.nz/property/insights/API/v1/Property/Insights.json" {:query-params {:place_id place-id :search_type 1}})]
    (when-not (:error @resp)
      (get-in (decode (:body @resp)) ["Results" 0 "PropertyDataGuid"]))))

(defn get-property-info [data-id]
  (timbre/info "Querying property details from trademe " data-id)
  (let [resp (http/get (str "https://www.trademe.co.nz/property/insights/API/v1/Property/Insights/Details/" data-id ".json"))]
    (when-not (:error @resp)
      (timbre/info (decode (:body @resp)))
      (decode (:body @resp)))))

(def cli-options
  [["-k" "--api-key APIKEY" "API KEY FROM GOOGLE"]
   ["-a" "--address ADDRESS" "ADDRESS"]])

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (when-not errors
      (let [{:keys [api-key address]} options]
        (some->
         (get-place-id address api-key)
            (get-property-data-guid)
            (get-property-info))))))
