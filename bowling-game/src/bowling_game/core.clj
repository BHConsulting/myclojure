(ns bowling_game.core)

(defn result
  "return a list of graphical notation of the results of all frames."
  [scorecard b1 b2]
  (if (= b1 10)
    (conj scorecard "X")
    (if (= (+ b1 b2) 10)
      (conj scorecard (str b1 "/"))
      (conj scorecard (str b1 " " b2)))))
