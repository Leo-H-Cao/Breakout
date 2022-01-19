package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelController {
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int FONT_SIZE = 50;

  private Group myRoot;
  private Scene myScene;
  private Ball ball;
  private Paddle paddle;
  private Block block;

  public LevelController(Group root, int width, int height, Paddle gamePaddle, Ball gameBall, Block gameBlock){
    myRoot = root;
    myScene = new Scene(root, width, height, BACKGROUND);
    paddle = gamePaddle;
    ball = gameBall;
    block = gameBlock;
  }

  public Scene getScene(){
    return myScene;
  }

//display start game button
  public void startGameScene(Button start_btn){
    start_btn.setLayoutX(Game.SIZE*(0.45));
    start_btn.setLayoutY(Game.SIZE*(0.55));
    Text startGameText = new Text(Game.SIZE*(0.25), Game.SIZE*(0.25), "Breakout");
    Font font = new Font(FONT_SIZE);
    startGameText.setFont(font);
    myRoot.getChildren().add(startGameText);
    myRoot.getChildren().add(start_btn);
  }

  //set scene for actual gameplay
  public void gameScene(){
    myRoot.getChildren().clear();
    myRoot.getChildren().add(block.getRectangle());
    myRoot.getChildren().add(ball.getImage());
    myRoot.getChildren().add(paddle.getRectangle());
  }

}
