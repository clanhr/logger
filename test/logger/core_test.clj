(ns logger.core-test
  (:require [clojure.test :refer :all]
            [logger.core :as core]))

(deftest transaction-id
  (let [tid (core/generate-transaction-id "aid" "uid")]
    (is tid)
    (is (re-matches #"aid:uid:\d+:\d+" tid))))
