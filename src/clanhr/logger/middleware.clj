(ns clanhr.logger.middleware
  "Ring middleware"
  (:require [environ.core :refer [env]]
            [result.core :as result]
            [clanhr.logger.core :as logger]))

(defn- get-tid
  "Gets or creates a tid"
  [context]
  (or (get-in context [:headers "x-clanhr-tid"])
      (logger/generate-transaction-id (get-in context [:principal :account])
                                      (get-in context [:principal :user-id]))))

(defn- log [data]
  (-> data
      (dissoc :compojure.api.middleware/options
              :ring.swagger.middleware/data)
      (logger/log)))

(defn run
  "Logs requests"
  [handler service-name]
  (fn [context]
    (let [tid (get-tid context)]
      (log (merge context {:tid tid
                           :service-name (name service-name)}))
      (let [response (handler (assoc context :tid tid))]
        (log response)
        response))))
