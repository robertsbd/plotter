package shapes;

import java.awt.Graphics;

/**
 *
 * @author benjamin
 */
public class Circle extends Shape {
    
    private double diameter;

    public Circle(){
        this.name = "CIRCLE";
    }
    
    public Circle(double x, double y, double diameter){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.name = "CIRCLE";
    }

    @Override
    public void draw(Graphics g){
        g.drawOval((int) x, (int) y, (int) diameter,  (int) diameter);
    }
    
    @Override
    public void fill(Graphics g){
        g.fillOval((int) x, (int) y, (int) diameter,  (int) diameter);        
    } 
}
