# Breakout Plan
### Leo Cao


## Interesting Breakout Variants

 * I found the "Centipong" variant to be very interesting because after the blocks are destroyed,
they bounce back as additional balls, making it more chaotic and fast-paced than the original

 * The "Bricks n Balls" variation was also interesting because it removed the paddle.
Instead, the player just shoots a stream of balls and watches as the balls destroy blocks and eventually bounce
bounce back, which I found very satisfying.


## Paddle Ideas

 * Player is allowed to move the paddle vertically to strike ball sooner/later instead of only horizontal movement

 * Warping from one side of the screen to the other when it reaches the edge


## Block Ideas

 * Taking multiple hits before being destroyed

 * Different shaped block such as a triangle so ball bounces differently

 * dropping a power-up when destroyed


## Power-up Ideas

 * wider paddle

 * extra ball in play

 * tiny ball (power-down)


## Cheat Key Ideas

 * ball damage increases

 * clears current level

 * extra lives

 * extra ball


## Level Descriptions

* "pictures":
  * level 1:
  
      1 1 1 1 1 1
    
      0 1 1 1 1 0
        
      0 0 1 1 0 0
  
  * level 2: 

    2 3 1 1 3 2

    2 3 1 1 3 2

    4 4 4 4 4 4

  * level 3:

    2 2 5 5 2 2

    1 5 5 5 5 1

    3 3 3 3 3 3

* Idea #1, certain power up blocks only appear on certain levels

* Idea #2, introduces triangle blocks

* Idea #3, paddle gets smaller as you lose lives


## Class Ideas

 * ball (method: setSpeed)

 * paddle (method: changeWidth)

 * block (method: makeInvisible)

 * collisions (method: objectsIntersecting)

