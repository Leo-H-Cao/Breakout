package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelController {
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int FONT_SIZE = 50;

  private Group myRoot;
  private Scene myScene;

  public LevelController(Group root, int width, int height){
    myRoot = root;
    myScene = new Scene(root, width, height, BACKGROUND);
  }

  public Scene getScene(){
    return myScene;
  }

  public void clearScene(){
    myRoot.getChildren().clear();
  }

  public void startGameScene(){
    Text startText = new Text(50, 70, "Start Game");
    Font font = new Font(FONT_SIZE);
    startText.setFont(font);
    myRoot.getChildren().add(startText);
  }

}
