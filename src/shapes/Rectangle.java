package shapes;

import java.awt.Graphics;

/**
 *
 * @author benjamin
 */
public class Rectangle extends Shape {
    
    private double width;
    private double height;

    public Rectangle(){
        super.name = "RECTANGLE";
    }
    
    public Rectangle(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = "RECTANGLE";
    }

    @Override
    public void draw(Graphics g){
        g.drawRect((int) x, (int) y, (int) width,  (int) height);
    }
    
    @Override
    public void fill(Graphics g){
        g.fillRect((int) x, (int) y, (int) width,  (int) height);        
    }
}
