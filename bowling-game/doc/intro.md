# Introduction to bowling-game

Ten-pin bowling game scorecard

Build an API that implements a ten-pin bowling scorecard (see also the wikipedia page).
(http://bowling.about.com/od/rulesofthegame/a/bowlingscoring.htm)
(https://en.wikipedia.org/wiki/Ten-pin_bowling#Scoring)

Requirements

We need a set of functions to implement a scorecard for a ten-pin bowling game. Don't worry about encoding a simulation for a game, we need an API that someone could use to implement a graphical representation of a game's scorecard. 
The minimal set of operations we want to support are:
1. Create an empty score card
 resetSC()
 initialize/reset the score card list!
2. Given a score card, score a frame
3. Determine whether a game is complete - if so, provide the final score
 total()

Please submit a git repo that contains your work (and your work alone). We'll be looking for your commit history to tell a story (albeit a short one!) and should include tests of some description.

Notes:

1. It will help to think through a simple data structure (or object, or type) to represent a scorecard 

2. Step 2 could have a signature like 

f(score_card, result_of_ball_1, result_of_ball_2)

and it returns a scorecard or a special value to signify an exception (FALSE, nil or an instance of Exception)  
(myI, passing the score_card here, the user may have though of a situation of multi-threading.., not to allow having a global variable from the API itself? (possibly!)

3. That said, Step 2 has (small, friendly!) dragons - namely the special cases of a spare and a strike, and are also scored differently for the final frame of the game - so feel free to adopt an entirely different representation.

4. This is not terribly complicated(!) - we're hoping that you have fun with it and that it provides a simple problem space within which to showcase some of your skills 
