# Breakout Design
## Leo Cao


## Design Goals
For this project I wanted to focus on the topics we discussed during the first couple weeks of class.
I tried my best to avoid duplicated code and long methods. Also, I put a lot of effort into 
planning out which classes I would need, and making sure each class had well-defined responsibilities.
I also tried to pay attention to the interactions between my various classes to reduce the number of dependencies between them.


## High-Level Design
From a high level, the main entities in the breakout game, the ball, block, and paddle, are their own separate classes.
The physics class defines how the different entities collide with each other. The game class focuses on the logic, keyboard/mouse inputs,
and setting up what is displayed for the game. The level controller controls which level to display on screen, as well as the start screen and end screens.

## Assumptions or Simplifications
* instead of having a block drop a power up, the power ups were presented as blocks
* Did not separate the display/view from the game logic itself
* Did not include all the power ups I wanted to, such as ball damage increase or multiple balls


## Changes from the Plan
* Subsituted some of the power ups from the plan for more simple ones
* Did not include any triangular blocks
* Did not allow paddle to warp from one side of the screen to the other


## How to Add New Levels
In order to add more levels, create .txt file that defines the layout for the level,
with numbers 1-3 for normal blocks of varying strength, 4-6 for power up blocks. The program will
then display the blocks accordingly. If we want to add more power ups, we can add an image for the power up to the resources folder.
This power up would be associated with a number that can be read in from the text file, and rendered as a special block.
We can then define the effects of the power up on the game logic in the game.java file.


