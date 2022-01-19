package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle{
    public static final Paint PADDLE_COLOR = Color.PLUM;
    public static final int PADDLE_WIDTH = 60;
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
        //checks is paddle is moving off screen
        if(x>=0 && x <= Game.SIZE-PADDLE_WIDTH){
            myRectangle.setX(x);
        }
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

    public Rectangle getRectangle(){
        return myRectangle;
    }

    public void setColor(Paint color){
        myRectangle.setFill(color);
    }

}
