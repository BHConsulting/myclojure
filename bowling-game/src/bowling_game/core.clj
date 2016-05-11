(ns bowling_game.core)

(defn isStrike "return true if strike"
  [b1]
  (= b1 10))
(defn isSpare "return true if spare"
  [b1 b2]
  (= (+ b1 b2) 10))
(defn is10thFrame "return true if last scorecard has 9 frames"
  [sc]
  (= (.length sc) 9))

(defn resultAFrame "result a given frame"
  ([b1] (resultAFrame b1 0))
  ([b1 b2] 
    (if (isStrike b1) "X"
      (if (isSpare b1 b2)
        (str b1 "/")
        (str b1 " " b2)))))
  
(defn result10thFrame "result 10th frame graphic"
  [b1 b2 b3]
    (if (isStrike b1)
      (if (isStrike b2)
        (if (isStrike b3)
          "XXX"
	  (str "XX" b3))
        (str "X" (resultAFrame b2 b3)))    
      (if (isSpare b1 b2)
        (str b1 "/" b3)
        (str b1 " " b2))))

(defn result
  "return a vector of graphical notation of the results of all frames."
  ([scorecard b1] (result scorecard b1 0 0))
  ([scorecard b1 b2] (result scorecard b1 b2 0))
  ([scorecard b1 b2 b3] (result scorecard b1 b2 b3 0 []))
  ([scorecard b1 b2 b3 indexFrame my-result]
  (if (or (> indexFrame 9) (> indexFrame (.length scorecard)))
    my-result
    (if (= indexFrame 9)
      (conj my-result (result10thFrame b1 b2 b3))
      (if (= indexFrame (.length scorecard))
	(conj my-result (resultAFrame b1 b2))
        (result scorecard b1 b2 b3
          (+ indexFrame 1)
	  (conj my-result (resultAFrame
	    ((scorecard indexFrame) 0)
	    ((scorecard indexFrame) 1)))))))))

(defn bonusStrike "calculate the strike bonus"
  [SC fIndex]
  (if (= fIndex 9)
    (+ ((SC fIndex) 1) ((SC fIndex) 2))
    (if (isStrike((SC (+ fIndex 1)) 0))
      (+ 10 ((SC (+ fIndex 2)) 0))
      (+ ((SC (+ fIndex 1)) 0) ((SC (+ fIndex 1)) 1)))))
(defn bonusSpare "calculate the spare bonus"
  [SC fIndex]
  (if (= fIndex 9)
    ((SC fIndex) 2)
    ((SC (+ fIndex 1)) 0)))

(defn scoreAFrame "score a given frame and return the number"
  [SC indexFrame] 
    (if (isStrike ((SC indexFrame) 0)) (+ 10 (bonusStrike SC indexFrame))
      (if (isSpare ((SC indexFrame) 0) ((SC indexFrame) 1))
        (+ 10 (bonusSpare SC indexFrame))
        (+ ((SC indexFrame) 0) ((SC indexFrame) 1)))))

(defn scoreFrame "score all frame"
  ([SC] (scoreFrame SC 0 []))
  ([SC indexFrame] (scoreFrame SC indexFrame []))
  ([SC indexFrame my-framescore]
  (if (or (> indexFrame 9) (> indexFrame (- (.length SC) 1)))
    my-framescore
    (scoreFrame
      SC
      (+ indexFrame 1)
      (conj my-framescore (scoreAFrame SC indexFrame))))))

(defn frameScore
  "return a vector of frame scores of all frames."
  ([scorecard] (scoreFrame scorecard))
  ([scorecard b1] (frameScore scorecard b1 0 0))
  ([scorecard b1 b2] (frameScore scorecard b1 b2 0))
  ([scorecard b1 b2 b3]
  (if (is10thFrame scorecard)
    (def SC (conj scorecard [b1 b2 b3]))
    (def SC (conj scorecard [b1 b2])))
  (scoreFrame SC 0)))

(defn totalFrame "return running total of each frame"
  ([SF] (totalFrame SF 0 []))
  ([SF indexFrame] (totalFrame SF indexFrame []))
  ([SF indexFrame my-totalscore]
    (if (or (> indexFrame 9) (> indexFrame (- (.length SF) 1)))
      my-totalscore
      (if (< (.length my-totalscore) 1)
        (totalFrame
          SF
          (+ indexFrame 1)
          (conj my-totalscore (+ 0 (SF indexFrame))))
        (totalFrame
          SF
          (+ indexFrame 1)
          (conj my-totalscore (+ (last my-totalscore) (SF indexFrame))))))))

(defn runningTotal
  "return a vector of running total of all frames."
  ([scorecard] (totalFrame (frameScore scorecard)))
  ([scorecard b1] (runningTotal scorecard b1 0 0))
  ([scorecard b1 b2] (runningTotal scorecard b1 b2 0))
  ([scorecard b1 b2 b3]
    (if (is10thFrame scorecard)
      (def SC (conj scorecard [b1 b2 b3]))
      (def SC (conj scorecard [b1 b2])))
    (totalFrame (frameScore SC b1 b2 b3))))

(defn empty-score "create an empty score card" [] [])

(defn isComplete "return final score or false"
  [scorecard]
  (if (< (.length scorecard) 10)
    false
    (if (and (= (.length (scorecard 9)) 2)
             (isSpare ((scorecard 9) 0) ((scorecard 9) 1)))
      false
      (last (runningTotal scorecard)))))
    