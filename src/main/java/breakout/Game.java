package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game {
    public static final int BALL_SIZE = 40;
    public static final int BOUNCER_SPEED = 50;
    public static final int PADDLE_SPEED = 16;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint BLOCK_COLOR = Color.GREENYELLOW;
    public static final int SIZE = 400;
    public static final String TITLE = "Breakout";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Ball myBall;
    private Scene scene;
    private Paddle myPaddle;
    private Block myBlock;
    private Physics gamePhysics;
    private LevelController myLevelController;

    public static void startGame(Stage stage){
        Scene scene = setupGame(SIZE, SIZE);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    public Scene setupGame (int width, int height) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();
        myBall = new Ball(width/2, height*(0.75), BALL_SIZE);
        myPaddle = new Paddle(width/2, height*(0.75) + BALL_SIZE);
        myBlock = new Block(0, 0, BLOCK_COLOR);
        gamePhysics = new Physics(myBall, myPaddle);

        myLevelController = new LevelController(root, width, height);
        scene = myLevelController.getScene();
        myLevelController.startGameScene();

        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBlock.getRectangle());
        root.getChildren().add(myBall.getImage());
        root.getChildren().add(myPaddle.getRectangle());

        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
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
            myBlock.setColor(HIGHLIGHT);
        }
        else {
            myBlock.setColor(BLOCK_COLOR);
        }
        if (isIntersecting(myBall, myPaddle.getRectangle())) {
            gamePhysics.ballAndPaddleBounce();
        }
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT) {
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
        else if (code == KeyCode.UP) {
            myPaddle.setY(myPaddle.getY() - PADDLE_SPEED);
        }
//        else if (code == KeyCode.DOWN) {
//            myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
//        }
    }

    private boolean isIntersecting (Ball a, Rectangle b) {
        return b.getBoundsInParent().intersects(a.getBounds());
    }
}
