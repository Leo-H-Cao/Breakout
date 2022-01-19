package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Block {
    public static final int BLOCK_SIZE = 40;

    private Rectangle myRectangle;
    private Paint myColor;
    private int health;

    public Block(double x, double y, int type){
        myRectangle = new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
        health = type;
        if(type == 1){
            myColor = Color.BLUE;
        }
        else if(type == 2){
            myColor = Color.PURPLE;
        }
        else if(type == 3){
            myColor = Color.RED;
        }
        else{
            this.removeBlock();
        }
        myRectangle.setFill(myColor);
    }

    public double getX(){
        return myRectangle.getX();
    }

    public double getY(){
        return myRectangle.getY();
    }

    public void setX(double x){
        myRectangle.setX(x);
    }

    public void setY(double y){
        myRectangle.setY(y);
    }

    public double getMaxX(){
        return myRectangle.getBoundsInParent().getMaxX();
    }

    public double getMaxY(){
        return myRectangle.getBoundsInParent().getMaxY();
    }

    public double getMinX(){
        return myRectangle.getBoundsInParent().getMinX();
    }

    public double getMinY(){
        return myRectangle.getBoundsInParent().getMinY();
    }

    public int getBlockHealth(){
        return health;
    }

    public void decHealth(){
        health--;
    }

    public void updateBlockColor(){
        if(health == 1){
            myColor = Color.BLUE;
        }
        else if(health == 2){
            myColor = Color.PURPLE;
        }
        else if(health == 3){
            myColor = Color.RED;
        }
        myRectangle.setFill(myColor);
    }

    //workaround because physics class still uses x and y of block after it has been deleted
    public void removeBlock(){
        myRectangle.setX(Game.SCREEN_SIZE*2);
        myRectangle.setY(Game.SCREEN_SIZE*2);
    }

    public Rectangle getRectangle(){
        return myRectangle;
    }

}
