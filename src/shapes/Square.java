package shapes;

import java.awt.Graphics;

/**
 *
 * @author benjamin
 */
public class Square extends Shape{
    private double width;
    private double x;
    private double y;

    public Square(){
    }
    
    public Square(double x, double y, double width){
        this.x = x;
        this.y = y;
        this.width = width;
    }

    @Override
    public void draw(Graphics g){
        g.drawRect((int) x, (int) y, (int) width,  (int) width);
    }
    
    @Override
    public void fill(Graphics g){
        g.fillRect((int) x, (int) y, (int) width,  (int) width);        
    }
    
}
