(ns clanhr.logger.middleware-test
  (:require [clojure.test :refer :all]
            [clanhr.logger.core :as core]
            [clanhr.logger.middleware :as logger-middleware])
  (:use ring.mock.request))

(deftest log-response-test
  (let [response-hash {:status 200 :headers {} :body "Foo"}]
    (testing "should call respond properly with response logger"
      (letfn [(handler [request]
                response-hash)]
        (let [req (request :get "/")
              response ((logger-middleware/run handler :bubu-service) req)]
          (is (= 200
                 (:status response))))))))
