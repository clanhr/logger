(ns logger.core
  "Generic logging abstraction")

(defn- timespan
  "Generates a timespan for now"
  []
  (int (/ (System/currentTimeMillis) 1000)))

(defn generate-transaction-id
  "Generates an unique transaction id"
  [account-id user-id]
  (str account-id ":" user-id ":" (timespan) ":" (rand-int 100)))

