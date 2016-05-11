(ns bowling_game.core)

(defn isStrike "return true if strike"
  [b1]
  (= b1 10))
(defn isSpare "return true if spare"
  [b1 b2]
  (= (+ b1 b2) 10))
(defn isLastFrame "return true if last scorecard has 9 frames"
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
  ([scorecard b1 b2 b3]
  (def my-result [])
  (loop [i 0]
    (when (< i (.length scorecard))
        (def my-result (conj my-result (resultAFrame ((scorecard i) 0) ((scorecard i) 1))))	
      (recur (inc i))))
  (if (isLastFrame scorecard)
    (conj my-result (result10thFrame b1 b2 b3))
    (conj my-result (resultAFrame b1 b2)))))

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

(defn scoreAFrame "score a given frame"
  [SC indexFrame] 
    (if (isStrike ((SC indexFrame) 0)) (+ 10 (bonusStrike SC indexFrame))
      (if (isSpare ((SC indexFrame) 0) ((SC indexFrame) 1))
        (+ 10 (bonusSpare SC indexFrame))
        (+ ((SC indexFrame) 0) ((SC indexFrame) 1)))))

(defn scoreFrame "score all frame"
  [SC]
  (def my-framescore [])
  (loop [i 0]
    (when (< i (.length SC))
      (def my-framescore (conj my-framescore (scoreAFrame SC i)))
      (recur (inc i))))
   my-framescore)

(defn frameScore
  "return a vector of frame scores of all frames."
  ([scorecard b1] (frameScore scorecard b1 0 0))
  ([scorecard b1 b2] (frameScore scorecard b1 b2 0))
  ([scorecard b1 b2 b3]
  (if (isLastFrame scorecard)
    (def SC (conj scorecard [b1 b2 b3]))
    (def SC (conj scorecard [b1 b2])))
  (scoreFrame SC)))
