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
  (if (isStrike b1)
    (def frameResult "X")
    (if (isSpare b1 b2)
      (def frameResult (str b1 "/"))
      (def frameResult (str b1 " " b2))))
  frameResult)     
  ([b1 b2 b3] ; 10th frame
  (def frameResult "")
  (if (isStrike b1)
    (if (isStrike b2)
      (if (isStrike b3)
        (def frameResult "XXX")
	(def frameResult (str "XX" b3)))
      (def frameResult (str "X" (resultAFrame b2 b3))))    
    (if (isSpare b1 b2)
      (def frameResult (str b1 "/" b3))
      (def frameResult (str b1 " " b2))))
  frameResult))
  
(defn result
  "return a list of graphical notation of the results of all frames."
  ([scorecard b1] (result scorecard b1 0 0))
  ([scorecard b1 b2] (result scorecard b1 b2 0))
  ([scorecard b1 b2 b3]
  (def my-result [])
  (loop [i 0]
    (when (< i (.length scorecard))
        (def my-result (conj my-result (resultAFrame ((scorecard i) 0) ((scorecard i) 1))))	
      (recur (inc i))))
  (if (isLastFrame scorecard)
    (conj my-result (resultAFrame b1 b2 b3))
    (conj my-result (resultAFrame b1 b2)))))
