package plotter;

/**
 *
 * @author benjamin
 */
public class Coordinates {
    
    private float x;
    private float y;
    
    private int xMap;
    private int yMap;
    
    static int xMin = 0; // this is the min of the coordinate system - we should only need to define this once at the start
    static int xMax = 0; // this is the max of the coordinate system - we should only need to define this once at the start
    static int yMin = 0; // this is the min of the coordinate system - we should only need to define this once at the start
    static int yMax = 0; // this is the max of the coordinate system - we should only need to define this once at the start
    
    static int frameWidth = 0;
    static int frameHeight = 0;
    static int padding = 0;
    
    Coordinates(int xMin, int xMax, int yMin, int yMax, int frameWidth, int frameHeight, int padding){ // this constructor is used to define the boundaries of the system and of the dimensions of the graphic space
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.padding = padding;
    }
    
    Coordinates(){
        this.x = 0;
        this.y = 0;
        this.xMap = mapX(this.x);
        this.yMap = mapY(this.y);
    }
    
    Coordinates(float x, float y){
        this.x = x;
        this.y = y;
        this.xMap = mapX(this.x);
        this.yMap = mapY(this.y);
    }

    private static int mapX(float x){ // maps from a standard cartesian space to javas graphic space taking into account any padding for margins
        float t = ((float) x - xMin) / ( xMax - xMin)*( frameWidth-2*padding)+padding;
        return (int) t;       
    }
 
    private static int mapY(float y){ 
        float t = -1*( (( (float) y - yMin)/(yMax-yMin))*(frameHeight-2*padding)+padding    )+frameHeight;
        return (int) t;
    }
    
    /**
     * @return the x
     */
    public float getX() {
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
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the xMapped
     */
    public int getxMap() {
        return xMap;
    }

    /**
     * @return the yMapped
     */
    public int getyMap() {
        return yMap;
    }
    

    
}
