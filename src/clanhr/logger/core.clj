(ns clanhr.logger.core
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

(defn- logstdout
  "Logs to stdout"
  [data]
  (when (env :clanhr-logger-log-stdout)
    (println (str "LOG " data))))

(defn log
  "Logs information"
  [data]
  (let [data (merge {:env (env :clanhr-env)
                     :Timestamp (timespan)}
                    data)]
    (logstdout data)))

