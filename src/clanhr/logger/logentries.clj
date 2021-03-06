(ns clanhr.logger.logentries
  "Send log data to logentries"
  (:require [environ.core :refer [env]]
            [aleph.http :as http]
            [cheshire.core :as json]))

(defn logentries-token
  "Token for logentries"
  []
  (or (env :clanhr-logger-logentries-token)
      "15d807a9-16d0-4da6-8afc-381f41061a43"))

(defn log
  "Sends log data to logentries, async"
  [data]
  (when (= "true" (env :clanhr-logger-log-logentries))
    (let [url (str "https://js.logentries.com/v1/logs/" (logentries-token))
          request-body (:body data)
          body (json/generate-string (dissoc data :body))
          data {:event body}]
      (http/post url {:body data}))))

(defn log-sync
  "Sends log sync"
  [data]
  (let [response @(log data)]
    (assoc response :data (slurp (:body response)))))
