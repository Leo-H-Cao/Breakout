# breakout
## Leo Cao

This project implements the game of Breakout.

### Timeline

Start Date: 1/10

Finish Date: 1/18

Hours Spent: 12

### Resources Used
* JavaFX documentation
* Stack Overflow


### Running the Program

Main class: Main

Data files needed: (all in resources folder) level1.txt, level2.txt, level3.txt,
basketball.png, bigball.png, paddlespeed.png, widepaddle.png

Key/Mouse inputs:
* Click start button to start game
* space bar to launch ball/space bar is also cheat key for bounce ball without hitting paddle
* arrow keys to move paddle left,right,up,down

Cheat keys:
* SPACE to bounce ball without hitting paddle
* C clears level
* B bigger ball
* L increases number of lives


### Notes/Assumptions

Assumptions or Simplifications:
* Did not implement all the power-ups I initially planned (such as a second ball or increasing ball damage)
* Also did not do any triangle shaped blocks
* Instead focused on more smooth interactions between ball and paddle, having ball bounce in different directions depending on where it hit on the paddle

Known Bugs:
* sometimes a single hit on the side of a block counts as more than a single hit, block loses 2-3 health
* if ball somehow hits side of paddle it can "stick/vibrate" before bouncing off

Extra features or interesting things we should not miss:
* Different power ups on each level, and power down (smaller ball) on the last level
* mechanics of ball and paddle collision , if ball hits on same side of paddle that it is coming from, bounces back in same direction

### Impressions
I found this project to be lots of fun. I have never coded a game from scratch like this before, and it was
nice being able to implement changes in the program and immediately test it out by playing the game. This pleasant experience
inspires me to do other things that are outside my comfort zone in terms of programming.

