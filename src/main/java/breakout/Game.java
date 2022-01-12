package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Game {
    public static final int BALL_SIZE = 40;
    public static final int BOUNCER_SPEED = 50;
    public static final int PADDLE_SPEED = 16;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint BLOCK_COLOR = Color.GREENYELLOW;

    private Ball myBall;
    private Scene scene;
    private Paddle myPaddle;
    private Block myBlock;

    public Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();
        myBall = new Ball(width/2, height/2, BALL_SIZE);
        myPaddle = new Paddle(100, 100);
        myBlock = new Block(150, 150, BLOCK_COLOR);

        // order added to the group is the order in which they are drawn

        root.getChildren().add(myBlock.getRectangle());
        root.getChildren().add(myBall.getImage());
        root.getChildren().add(myPaddle.getRectangle());

        // create a place to see the shapes
        scene = new Scene(root, width, height, background);

        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    public void step (double elapsedTime) {
        if (myBall.getMaxX() > scene.getWidth() || myBall.getMinX() < 0) {
            myBall.wallBounceX();
        }

        if (myBall.getMaxY() > scene.getHeight() || myBall.getMinY() < 0) {
            myBall.wallBounceY();
        }

        myBall.setX(myBall.getX() + (BOUNCER_SPEED * elapsedTime) * myBall.getDirX());
        myBall.setY(myBall.getY() + (BOUNCER_SPEED * elapsedTime) * myBall.getDirY());

        // check for collisions
        if (isIntersecting(myBall, myBlock.getRectangle())) {
            myBlock.setColor(HIGHLIGHT);
        }
        else {
            myBlock.setColor(BLOCK_COLOR);
        }
        if (isIntersecting(myBall, myPaddle.getRectangle())) {
            myBall.wallBounceX();
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
        else if (code == KeyCode.DOWN) {
            myPaddle.setY(myPaddle.getY() + PADDLE_SPEED);
        }
    }

    private boolean isIntersecting (Ball a, Rectangle b) {
        return b.getBoundsInParent().intersects(a.getBounds());
    }
}
