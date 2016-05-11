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
(deftest f-test
  (testing "frame result -Open"
    (def b [[7 2]])
    (def result-frame (scoreFrame b))
    (is (= result-frame [9]))))
(deftest g-test
  (testing "frame result -Spare"
    (def b [[7 3] [5 0]])
    (def result-frame (scoreFrame b))
    (is (= result-frame [15 5]))))
(deftest h-test
  (testing "frame result -Strike"
    (def b [[10] [5 1]])
    (def result-frame (scoreFrame b))
    (is (= result-frame [16 6]))))

(deftest i-test
  (testing "frame results -OPen"
    (def b [])
    (def result-frame (frameScore b 7 2))
    (is (= result-frame [9]))))
(deftest j-test
  (testing "frame results - mixed"
    (def b [[10] [5 5]])
    (def result-frame (frameScore b 7 2))
    (is (= result-frame [20 17 9]))))
(deftest k-test
  (testing "frame results - complete"
    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (def result-frame (frameScore b 10 10 3))
    (is (= result-frame [20 17 9 20 30 22 15 5 20 23]))))

(deftest l-test
  (testing "running total"
    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (def result-frame (runningTotal b 7 3 3))
    (is (= result-frame [20 37 46 66 96 118 133 138 155 168]))))

(deftest m-test
  (testing "create an empty score card"
    (is (= (runningTotal (empty-score)) []))))

(deftest n-test
  (testing "determine whethere a game is complete - if so provide the final score"
    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4] [7 3 3]])
    (is (= (isComplete b) 168))))

(deftest p-test
  (testing "f test - throws an exception when the input is not satisfactory"
    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (is (= (f b 7 3 3) {:Complete 168 :lastDragons "SPARE"
    	      	        :runningTotal [20 37 46 66 96 118 133 138 155 168]
			:scoreFrame [20 17 9 20 30 22 15 5 17 13]
			:result ["X" "7/" "7 2" "9/" "X" "X" "X" "2 3" "6/" "7/3"]}))))

;(deftest q-test
;  (testing "f test - throws an exception when the input is not satisfactory"
;    (def b '(1 2))
;    (is (= (f b 3) false))))
