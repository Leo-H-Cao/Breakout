package breakout;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game {
    public static final int BALL_SIZE = 30;
    public static final int BIG_BALL_SIZE = 40;
    public static final int SMALL_BALL_SIZE = 20;
    public static final int BOUNCER_SPEED = 50;
    public static final int DEFAULT_PADDLE_SPEED = 24;
    public static final int SCREEN_SIZE = 400;
    public static final String TITLE = "Breakout";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int INITIAL_BALL_VELOCITY = -3;
    public static final int BLOCK_GAP = 10;

    private Ball myBall;
    private Scene scene;
    private Paddle myPaddle;
    private Physics gamePhysics;
    private LevelController myLevelController;
    private Group root;
    private ArrayList<Block> blocks;
    private ArrayList<Integer> blocksList;
    private int numBlocks;
    private int blankBlocks;
    private boolean ballLaunched;
    private int paddleSpeed;

    public void startGame(Stage stage){
        root = new Group();
        myBall = new Ball(SCREEN_SIZE*(0.5), SCREEN_SIZE*(0.75), BALL_SIZE);
        myPaddle = new Paddle(SCREEN_SIZE*(0.47), SCREEN_SIZE*(0.75) + BALL_SIZE);
        gamePhysics = new Physics(myBall, myPaddle);
        blocksList = new ArrayList<>();
        blocks = new ArrayList<>();
        myLevelController = new LevelController(root, SCREEN_SIZE, SCREEN_SIZE, myPaddle, myBall, blocks);
        setupBlocks(myLevelController.getLevel());
        paddleSpeed = DEFAULT_PADDLE_SPEED;

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

        if (myBall.getMinY() < 0) {
            myBall.bounceY();
        }

        if(myBall.getMinY() > scene.getHeight()){
            myLevelController.decLives();
            gamePhysics.resetBallAndPaddle();
            ballLaunched = false;
            paddleSpeed = DEFAULT_PADDLE_SPEED;

            if(myLevelController.getLives() == 0){
                myLevelController.loseScene();
            }
        }

        myBall.setX(myBall.getX() + (BOUNCER_SPEED * elapsedTime) * myBall.getVelocityX());
        myBall.setY(myBall.getY() + (BOUNCER_SPEED * elapsedTime) * myBall.getVelocityY());

        // check for collisions
        for(Block block: blocks){
            if (isIntersecting(myBall, block.getRectangle())) {
                gamePhysics.ballAndBlockBounce(block);
                block.decHealth();
                if(block.getBlockHealth() == 0){
                    block.removeBlock();
                    root.getChildren().remove(block.getRectangle());
                    numBlocks--;
                    if(block.getType() == 4){
                        myBall.setSize(SMALL_BALL_SIZE);
                    }
                    else if(block.getType() == 5){
                        myPaddle.widePaddle();
                    }
                    else if(block.getType() == 6){
                        paddleSpeed *= 1.3;
                    }
                }
                else{
                    block.updateBlockColor();
                }
            }
        }

        if (isIntersecting(myBall, myPaddle.getRectangle())) {
            gamePhysics.ballAndPaddleBounce();
        }

        if(numBlocks == 0){
            nextLevel();
        }
    }

    private void handleStartButtonClick(Stage stage){
        myLevelController.gameScene();
        scene = myLevelController.getScene();
        stage.setScene(scene);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.RIGHT && ballLaunched) {
            myPaddle.setX(myPaddle.getX() + paddleSpeed);
        }
        else if (code == KeyCode.LEFT && ballLaunched) {
            myPaddle.setX(myPaddle.getX() - paddleSpeed);
        }

        //initially launches ball but also cheat key to bounce ball without hitting paddle
        else if(code == KeyCode.SPACE){
            myBall.setVelocityY(INITIAL_BALL_VELOCITY);
            myBall.setVelocityX(Physics.getRandomVelocity());
            ballLaunched = true;
        }

        //cheat key clears level
        else if(code == KeyCode.C){
            myLevelController.clearBlocks();
            numBlocks = 0;
            blankBlocks = 0;
        }

        //cheat code to make larger ball
        else if(code == KeyCode.B){
            myBall.setSize(BIG_BALL_SIZE);
        }

        //cheat code to increase number of lives
        else if(code == KeyCode.L){
            myLevelController.incLives();
        }
        else if (code == KeyCode.DOWN && ballLaunched) {
            myPaddle.setY(myPaddle.getY() + paddleSpeed);
        }
        else if (code == KeyCode.UP && ballLaunched) {
            myPaddle.setY(myPaddle.getY() - paddleSpeed);
        }
    }

    private boolean isIntersecting (Ball a, Rectangle b) {
        return b.getBoundsInParent().intersects(a.getBounds());
    }

    private void setupBlocks(int level){
        int xPos = 0;
        int yPos = 0;
        String filePath = "level"+level+".txt";
        readFile(filePath);
        for(Integer blockType : blocksList){
            Block block = new Block(xPos, yPos, blockType);
            blocks.add(block);
            if(blockType != 0){
                numBlocks++;
            }
            else{
                blankBlocks++;
            }
            if((numBlocks+blankBlocks) % 8 == 0){
                xPos = 0;
                yPos += (Block.BLOCK_SIZE + BLOCK_GAP);
            }
            else{
                xPos += (Block.BLOCK_SIZE + BLOCK_GAP);
            }
        }
    }

    private void readFile(String filePath) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(filePath));
        while (scanner.hasNextInt()) {
            blocksList.add(scanner.nextInt());
        }
        scanner.close();
    }

    public void nextLevel(){
        blocks.clear();
        blocksList.clear();
        myLevelController.incLevel();
        int level = myLevelController.getLevel();
        if(level <= 3){
            setupBlocks(level);
            myLevelController.gameScene();
            gamePhysics.resetBallAndPaddle();
            ballLaunched = false;
        }
        else{
            myLevelController.winScene();
        }
    }
}
