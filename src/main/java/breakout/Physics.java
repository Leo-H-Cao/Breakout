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
      if (ball.getVelocityX() > 0 && (minXPad + Paddle.PADDLE_WIDTH / 2) > maxXBall) {
        ball.bounceX();
      }
      if (ball.getVelocityX() < 0 && (minXPad + Paddle.PADDLE_WIDTH / 2) < minXBall) {
        ball.bounceX();
      }
    }
  }

  public static int getRandomVelocity(){
    int random_int = (int)Math.floor(Math.random()*(MAX_BALL_SPEED-MIN_BALL_SPEED+1)+MIN_BALL_SPEED);
    return random_int;
  }

}
