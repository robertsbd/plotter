package data;

/**
 *
 * @author benjamin
 */
public class Coordinates {
    
    private double x;
    private double y;
    
    private int xMap;
    private int yMap;
    
    private static int xMin = 0; // this is the min of the coordinate system - we should only need to define this once at the start
    private static int xMax = 0; // this is the max of the coordinate system - we should only need to define this once at the start
    private static int yMin = 0; // this is the min of the coordinate system - we should only need to define this once at the start
    private static int yMax = 0; // this is the max of the coordinate system - we should only need to define this once at the start
    
    static int frameWidth = 0;
    static int frameHeight = 0;
    static int padding = 0;
    
    public Coordinates(int xMin, int xMax, int yMin, int yMax, int frameWidth, int frameHeight, int padding){ // this constructor is used to define the boundaries of the system and of the dimensions of the graphic space
        Coordinates.xMin = xMin;
        Coordinates.xMax = xMax;
        Coordinates.yMin = yMin;
        Coordinates.yMax = yMax;
        Coordinates.frameWidth = frameWidth;
        Coordinates.frameHeight = frameHeight;
        Coordinates.padding = padding;
    }
    
    public Coordinates(){
        this.x = 0;
        this.y = 0;
        this.xMap = mapX(this.x);
        this.yMap = mapY(this.y);
    }
    
    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
        this.xMap = mapX(this.x);
        this.yMap = mapY(this.y);
    }

    private static int mapX(double x){ // maps from a standard cartesian space to javas graphic space taking into account any padding for margins
        double t = ((double) x - xMin) / ( xMax - xMin)*( frameWidth-2*padding)+padding;
        return (int) t;       
    }
 
    private static int mapY(double y){ 
        double t = -1*( (( (double) y - yMin)/(yMax-yMin))*(frameHeight-2*padding)+padding)+frameHeight;
        return (int) t;
    }
    
    /**
     * @return the x
     */
    public double getX() {
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
    public double getY() {
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
    
    /**
     * @return the xMin
     */
    public static int getxMin() {
        return xMin;
    }

    /**
     * @return the xMax
     */
    public static int getxMax() {
        return xMax;
    }

    /**
     * @return the yMin
     */
    public static int getyMin() {
        return yMin;
    }

    /**
     * @return the yMax
     */
    public static int getyMax() {
        return yMax;
    }
    
}
