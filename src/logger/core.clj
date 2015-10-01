(ns logger.core
  "Generic logging abstraction"
  (:require [environ.core :refer [env]]))

(defn- timespan
  "Generates a timespan for now"
  []
  (int (/ (System/currentTimeMillis) 1000)))

(defn generate-transaction-id
  "Generates an unique transaction id"
  [account-id user-id]
  (str account-id ":" user-id ":" (timespan) ":" (rand-int 100)))

(defn log
  "Logs information"
  [data]
  (let [data (merge {:env (env :clanhr-env)}
                    data)]
    (println (str "LOG " data))))

