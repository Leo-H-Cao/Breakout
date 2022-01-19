package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game {
    public static final int BALL_SIZE = 40;
    public static final int BOUNCER_SPEED = 50;
    public static final int PADDLE_SPEED = 20;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint BLOCK_COLOR = Color.GREENYELLOW;
    public static final int SIZE = 400;
    public static final String TITLE = "Breakout";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int INITIAL_BALL_VELOCITY = -3;

    private Ball myBall;
    private Scene scene;
    private Paddle myPaddle;
    private Block myBlock;
    private Physics gamePhysics;
    private LevelController myLevelController;

    public void startGame(Stage stage){
        Group root = new Group();
        myBall = new Ball(SIZE/2, SIZE*(0.75), BALL_SIZE);
        myPaddle = new Paddle(SIZE/2, SIZE*(0.75) + BALL_SIZE);
        myBlock = new Block(0, 0, BLOCK_COLOR);
        gamePhysics = new Physics(myBall, myPaddle, myBlock);
        myLevelController = new LevelController(root, SIZE, SIZE, myPaddle, myBall, myBlock);
        scene = myLevelController.getScene();
        Button start_btn = new Button("Start");
        myLevelController.startGameScene(start_btn);
        start_btn.setOnAction(e-> handleStartButtonClick(stage));
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }


    public void step (double elapsedTime) {
        if (myBall.getMaxX() > scene.getWidth() || myBall.getMinX() < 0) {
            myBall.bounceX();
        }

        if (myBall.getMaxY() > scene.getHeight() || myBall.getMinY() < 0) {
            myBall.bounceY();
        }

        myBall.setX(myBall.getX() + (BOUNCER_SPEED * elapsedTime) * myBall.getVelocityX());
        myBall.setY(myBall.getY() + (BOUNCER_SPEED * elapsedTime) * myBall.getVelocityY());

        // check for collisions
        if (isIntersecting(myBall, myBlock.getRectangle())) {
            gamePhysics.ballAndBlockBounce();
            myBlock.setColor(HIGHLIGHT);
        }
        else {
            myBlock.setColor(BLOCK_COLOR);
        }
        if (isIntersecting(myBall, myPaddle.getRectangle())) {
            gamePhysics.ballAndPaddleBounce();
        }
    }

    private void handleStartButtonClick(Stage stage){
        myLevelController.gameScene();
        scene = myLevelController.getScene();
        stage.setScene(scene);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
        else if(code == KeyCode.SPACE){
            myBall.setVelocityY(INITIAL_BALL_VELOCITY);
            myBall.setVelocityX(Physics.getRandomVelocity());
        }
//        else if (code == KeyCode.DOWN) {
//            myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
//        }
//        else if (code == KeyCode.UP) {
//            myPaddle.setY(myPaddle.getY() - PADDLE_SPEED);
//        }
    }

    private boolean isIntersecting (Ball a, Rectangle b) {
        return b.getBoundsInParent().intersects(a.getBounds());
    }
}
