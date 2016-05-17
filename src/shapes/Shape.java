package shapes;

import java.awt.Graphics;

/**
 *
 * @author benjamin
 */
public abstract class Shape {
    protected String name;
    protected double x;
    protected double y;
    
    public abstract void draw(Graphics g);
    public abstract void fill(Graphics g);
    
    public String getName(){
        return this.name;
    }
}
