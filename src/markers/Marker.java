package markers;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author benjamin
 */
public abstract class Marker {
    protected double x;
    protected double y;
    protected double size;
    protected Color color;
    protected boolean outline;
    
    public abstract void draw(Graphics g);
    public abstract void fill(Graphics g);
   
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
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
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the size
     */
    public double getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the isOutline
     */
    public boolean isOutline() {
        return outline;
    }

    /**
     * @param outline the isOutline to set
     */
    public void setOutline(boolean outline) {
        this.outline = outline;
    }
}
