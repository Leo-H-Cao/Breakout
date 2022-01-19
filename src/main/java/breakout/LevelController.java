package breakout;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelController {
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int FONT_SIZE = 18;
    public static final int TITLE_FONT_SIZE = 50;


    private Group myRoot;
    private Scene myScene;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Block> blocks;
    private int level;
    private int lives;
    private Text levelsText;
    private Text livesText;


    public LevelController(Group root, int width, int height, Paddle gamePaddle, Ball gameBall, ArrayList<Block> gameBlocks){
        myRoot = root;
        myScene = new Scene(root, width, height, BACKGROUND);
        paddle = gamePaddle;
        ball = gameBall;
        blocks = gameBlocks;
        level = 1;
        lives = 3;
    }

    public Scene getScene(){
        return myScene;
    }

  //display start game button
    public void startGameScene(Button start_btn){
        start_btn.setLayoutX(Game.SCREEN_SIZE*(0.45));
        start_btn.setLayoutY(Game.SCREEN_SIZE*(0.55));
        Text startGameText = new Text(Game.SCREEN_SIZE*(0.25), Game.SCREEN_SIZE*(0.25), "Breakout");
        Font font = new Font(TITLE_FONT_SIZE);
        startGameText.setFont(font);
        myRoot.getChildren().add(startGameText);
        myRoot.getChildren().add(start_btn);
    }

    //set scene for actual gameplay
    public void gameScene(){
        myRoot.getChildren().clear();
        for(Block block : blocks){
          myRoot.getChildren().add(block.getRectangle());
        }
        myRoot.getChildren().add(ball.getImage());
        myRoot.getChildren().add(paddle.getRectangle());

        livesText = new Text(Game.SCREEN_SIZE*(0.83), Game.SCREEN_SIZE*(0.97), "Lives: " + lives);
        Font font = new Font(FONT_SIZE);
        livesText.setFont(font);
        myRoot.getChildren().add(livesText);

        levelsText = new Text(Game.SCREEN_SIZE*(0.02), Game.SCREEN_SIZE*(0.97), "Level: " + level);
        levelsText.setFont(font);
        myRoot.getChildren().add(levelsText);
    }

    public void clearBlocks(){
      for(Block block : blocks){
          block.removeBlock();
          myRoot.getChildren().remove(block.getRectangle());
      }
    }

    public void incLevel(){
      level++;
      levelsText.setText("Level: " + level);
    }

    public int getLevel(){
      return level;
    }

    public void decLives(){
      lives--;
      livesText.setText("Lives: " + lives);
    }

    public int getLives(){
      return lives;
    }

    public void loseScene(){
      myRoot.getChildren().clear();
      Text lostText = new Text(Game.SCREEN_SIZE*(0.25), Game.SCREEN_SIZE*(0.25), "Game Over");
      Font font = new Font(TITLE_FONT_SIZE);
      lostText.setFont(font);
      myRoot.getChildren().add(lostText);
    }

}
