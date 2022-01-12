package breakout;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball{
    public static final String RESOURCE_PATH = "/";
    public static final String BALL_IMAGE = RESOURCE_PATH + "basketball.png";

    private ImageView myImage;
    private int myDirX;
    private int myDirY;

    public Ball(double x, double y, int size){
        Image image = new Image(getClass().getResourceAsStream(BALL_IMAGE));
        myImage = new ImageView(image);
        myImage.setFitWidth(size);
        myImage.setFitHeight(size);
        myImage.setX(x);
        myImage.setY(y);
        myDirX = 3;
        myDirY = 1;
    }

    public void setX(double x){
        myImage.setX(x);
    }

    public void setY(double y){
        myImage.setY(y);
    }

    public double getSize(){
        return myImage.getFitWidth();
    }

    public int getDirX(){
        return myDirX;
    }

    public void setDirX(int dir){
        myDirX= dir;
    }
    public int getDirY(){
        return myDirY;
    }

    public void setDirY(int dir){
        myDirY= dir;
    }

    public double getX(){
        return myImage.getX();
    }

    public double getMinX(){
        return myImage.getX();
    }

    public double getMaxX(){
        return myImage.getX() + myImage.getFitWidth();
    }

    public double getY(){
        return myImage.getY();
    }

    public double getMinY(){
        return myImage.getY();
    }

    public double getMaxY(){
        return myImage.getY() + myImage.getFitWidth();
    }

    public void wallBounceX(){
        myDirX = -myDirX;
    }

    public void wallBounceY(){
        myDirY = -myDirY;
    }

    public Bounds getBounds(){
        return myImage.getBoundsInLocal();
    }

    public ImageView getImage(){
        return myImage;
    }

}
