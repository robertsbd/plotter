package markers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author benjamin
 */
public class Circle extends Marker {

    public Circle(){
    }

    // size is the diameter
    public Circle(double size, Color color, boolean outline){
        super.color = color;
        super.size = size;
        super.outline = outline;
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        g2d.setColor(Color.BLACK);
        g2d.drawOval((int) x, (int) y, (int) size,  (int) size);
    }
    
    @Override
    public void fill(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        g2d.setColor(color);        
        g2d.fillOval((int) x, (int) y, (int) size,  (int) size);        
    } 
}
