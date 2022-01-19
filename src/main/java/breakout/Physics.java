package breakout;

public class Physics {
  public static final int MAX_BALL_SPEED = 5;
  public static final int MIN_BALL_SPEED = 2;

  private Ball ball;
  private Paddle paddle;

  public Physics(Ball gameBall, Paddle gamePaddle){
    ball = gameBall;
    paddle = gamePaddle;
  }
  public void ballAndPaddleBounce(){
    double minXPad = paddle.getMinX();
    double maxXPad = paddle.getMaxX();
    double minYPad = paddle.getMinY();
    double minXBall = ball.getMinX();
    double maxXBall = ball.getMaxX();
    double minYBall = ball.getMinY();
    double maxYBall = ball.getMaxY();

    //makes sure ball bounces on top of paddle, if hit from bottom, does not bounce
    if (minYBall <= minYPad && maxYBall >= minYPad && ((maxXBall >= minXPad)
        && (maxXBall <= maxXPad) && ball.getVelocityY() > 0
        || (minXBall >= minXPad && minXBall <= maxXPad))) {
      ball.bounceY();

      //if coming from left/right and hits on near same sided edge of paddle, bounces back in same left/right direction
      if (ball.getVelocityX() > 0 && (minXPad + Paddle.PADDLE_WIDTH *(0.5)) > maxXBall) {
        ball.bounceX();
      }
      if (ball.getVelocityX() < 0 && (minXPad + Paddle.PADDLE_WIDTH *(0.5)) < minXBall) {
        ball.bounceX();
      }
    }
  }

  public void ballAndBlockBounce(Block block){
    double minXBlock = block.getMinX();
    double maxXBlock = block.getMaxX();
    double minYBlock = block.getMinY();
    double maxYBlock = block.getMaxY();
    double minXBall = ball.getMinX();
    double maxXBall = ball.getMaxX();
    double minYBall = ball.getMinY();
    double maxYBall = ball.getMaxY();

    //check if ball collides on top or bottom edge of block
    boolean ballHitsXDirection = ((minXBall >= minXBlock && minXBall <= maxXBlock) || (maxXBall >= minXBlock) && (maxXBall <= maxXBlock));
    boolean ballHitsYDirection = (maxYBall >= minYBlock && maxYBall <= maxYBlock) || (minYBall >= minYBlock && minYBall <= maxYBlock);
    if((ballHitsXDirection && ball.getVelocityY() > 0 && minYBall <= minYBlock && maxYBall >= minYBlock) ||
        (ballHitsXDirection && ball.getVelocityY() < 0 && minYBall <= maxYBlock && maxYBall >= maxYBlock)){
      ball.bounceY();
    }
    //check if ball hits left or right edge
    else if((ball.getVelocityX() > 0 && maxXBall >= minXBlock && minXBall <= minXBlock && ballHitsYDirection)
        || (ball.getVelocityX() < 0 && minXBall <= maxXBlock && maxXBall >= maxXBlock && ballHitsYDirection)){
      ball.bounceX();
    }
  }

  public static int getRandomVelocity(){
    int randomInt = (int)Math.floor(Math.random()*(MAX_BALL_SPEED-MIN_BALL_SPEED+1)+MIN_BALL_SPEED);
    if(Math.random() < 0.5){
      randomInt = randomInt * -1;
    }
    return randomInt;
  }

  public void resetBallAndPaddle(){
    ball.setX(Game.SCREEN_SIZE*(0.5));
    ball.setY(Game.SCREEN_SIZE*(0.75));
    paddle.setX(Game.SCREEN_SIZE*(0.47));
    paddle.setY(Game.SCREEN_SIZE*(0.75) + Game.BALL_SIZE);
    ball.setVelocityY(0);
    ball.setVelocityX(0);
  }

}
