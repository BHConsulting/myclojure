# myclojure
Welcoe to my little bowling clojure project

The data structure for 'scorecard' parameter is expected to have a vector of vectors including the rolls of each frame in the inner vector. For example, [[10] [10 0] [5 5] ... [7 3 3]]. 
This was the simplest way of handling the various formats of data in returned results, so it was chosen that way. And it worked out nicely.

These are the core functions to support building a ten-pin bowling scorecard:
- empty-score ()
 to return an empty vector structure for further communications.

- frameScore (scorecard (, result_of_ball_1, result_of_ball_2, result_of_ball_3))
 to return frame score as described in the example of given ten-pin bowling scorecard page.

- runningTotal (scorecard (,result_of_ball_1, result_of_ball_2, result_of_ball_3))
 to return the running total as described in the example of given ten-pin bowling scorecard page.

- isComplete (scorecard (,result_of_ball_1, result_of_ball_2, result_of_ball_3))
 to return the total score of game or false status when it is not complete.

- result (scorecard (, result_of_ball_1, result_of_ball_2, result_of_ball_3))
 to display the graphical notation of each frame result as described in the example of given ten-pin bowling scorecard. 

- f (scorecard, (result_of_ball_1, result_of_ball_2, result_of_ball_3))
 to return a map including the followings:
  the total score or false status when the game is not completed,
  dragons of STRIKE/SPARE/OPEN for the last rolls of the frame,
  running total vector display,
  frame score of every frame
  and result of graphical notations.


These are the test outcome in the final stage of the API.

    (def b [])
    (result b 10 0))
;=> "X"

    (def b [])
    (result b 5 5)
;=> ["5/"]

    (def b [])
    (result b 5 4)
;=> ["5 4"]

    (def b [[10 0] [5 5]])
    (result b 5 4)
;=> ["X" "5/" "5 4"]

    (def b [[10 0] [5 5] [10 0] [10 0] [10 0] [10 0] [10 0] [10 0] [10 0]])
    (result b 7 3 3)
;=> ["X" "5/" "X" "X" "X" "X" "X" "X" "X" "7/3"]

    (def b [[7 2]])
    (scoreFrame b)
;=> [9]

    (def b [[7 3] [5 0]])
    (scoreFrame b)
;=> [15 5]

    (def b [[10] [5 1]])
    (scoreFrame b)
;=> [16 6]

    (def b [])
    (frameScore b 7 2)
;=> [9]

    (def b [[10] [5 5]])
    (frameScore b 7 2)
;=> [20 17 9]

    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (frameScore b 10 10 3)
;=> [20 17 9 20 30 22 15 5 20 23]

    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (runningTotal b 7 3 3)
;=> [20 37 46 66 96 118 133 138 155 168]

(empty-score) 
;=> []

    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4] [7 3 3]])
    (isComplete b)
;=> 168

    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4] [7 3]])
    (isComplete b)
;=> false

    (def b [[10] [7 3] [7 2] [9 1] [10] [10] [10] [2 3] [6 4]])
    (f b 7 3 3) 
;=> {:Complete 168 :lastDragons "SPARE"
     :runningTotal [20 37 46 66 96 118 133 138 155 168]
     :scoreFrame [20 17 9 20 30 22 15 5 17 13]
     :result ["X" "7/" "7 2" "9/" "X" "X" "X" "2 3" "6/" "7/3"]}

    (def b '(1 2))
    (f b 3)
=>   java.lang.Exception: data exception in bowling_pin game!!!
 at bowling_game.core$f.invokeStatic (core.clj:153)

