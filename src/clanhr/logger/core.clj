(ns clanhr.logger.core
  "Generic logging abstraction"
  (:require [environ.core :refer [env]]
            [clanhr.logger.logentries :as logentries]
            [result.core :as result]))

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
  (when (= "true" (env :clanhr-logger-log-stdout))
    (println data)))

(defn log
  "Logs information"
  [data]
  (try
    (let [data (merge {:env (env :clanhr-env)
                       :t (timespan)}
                      data)]
      (logstdout data)
      (result/success))
    (catch Exception e
      (println e)
      (result/exception e))))


