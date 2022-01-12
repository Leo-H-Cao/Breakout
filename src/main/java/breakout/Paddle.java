package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle{
    public static final Paint PADDLE_COLOR = Color.PLUM;
    public static final int PADDLE_WIDTH = 50;
    public static final int PADDLE_HEIGHT = 15;

    private Rectangle myRectangle;

    public Paddle(double x, double y){
        myRectangle = new Rectangle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        myRectangle.setFill(PADDLE_COLOR);
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

    public Rectangle getRectangle(){
        return myRectangle;
    }

    public void setColor(Paint color){
        myRectangle.setFill(color);
    }
}
