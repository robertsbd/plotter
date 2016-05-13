package plotter;

/**
 *
 * @author benjamin
 */
public class Coordinates {
    
    private int x;
    private int y;
    
    Coordinates(){
        this.x = 0;
        this.y = 0;
    }
    
    Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public int mapX(int frameWidth){ // maps from a standard cartesian space to javas graphic space
        return this.x + (int) (frameWidth/2);       
    }
 
    public int mapY(int frameHeight){ 
        return (-1*this.y) + (int) (frameHeight/2);
    }
    
    public int reverseMapX(int frameWidth){ // maps from a standard cartesian space to javas graphic space
        return this.x - (int) frameWidth/2;       
    }
 
    public int reverseMapY(int frameHeight){ // maps from javas graphic space to standard cartesian space
        return (-1*this.y) + (int) frameHeight/2;
    }
    
}
