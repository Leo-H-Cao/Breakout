package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Block {
    public static final int BLOCK_SIZE = 40;

    private Rectangle myRectangle;
    private Paint myColor;

    public Block(double x, double y, Paint color){
        myRectangle = new Rectangle(x, y, BLOCK_SIZE, BLOCK_SIZE);
        myRectangle.setFill(color);
        myColor = color;
    }

    public double getX(){
        return myRectangle.getX();
    }

    public double getY(){
        return myRectangle.getY();
    }

    public Rectangle getRectangle(){
        return myRectangle;
    }

    public void setColor(Paint color){
        myRectangle.setFill(color);
    }
}
