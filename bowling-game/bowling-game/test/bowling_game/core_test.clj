(ns bowling-game.core-test
  (:require [clojure.test :refer :all]
            [bowling_game.core :refer :all]))

(deftest a-test
  (testing "test results. strike"
    (def b [])
    (def resultsc (result b 10 0))
    (is (= resultsc ["X"]))))

(deftest b-test
  (testing "test results. spare "
    (def b [])
    (def resultsc (result b 5 5))
    (is (= resultsc ["5/"]))))

(deftest c-test
  (testing "test results. open"
    (def b [])
    (def resultsc (result b 5 4))
    (is (= resultsc ["5 4"]))))

(deftest d-test
  (testing "test results. multi frame"
    (def b [[10 0] [5 5]])
    (def resultsc (result b 5 4))
    (is (= resultsc ["X" "5/" "5 4"]))))

(deftest e-test
  (testing "test results. last frame"
    (def b [[10 0] [5 5] [10 0] [10 0] [10 0] [10 0] [10 0] [10 0] [10 0]])
    (def resultsc (result b 7 3 3))
    (is (= resultsc ["X" "5/" "X" "X" "X" "X" "X" "X" "X" "7/3"]))))
