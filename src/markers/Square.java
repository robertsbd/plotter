package markers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author benjamin
 */
public class Square extends Marker{

    public Square(){
    }
    
    public Square(double size, Color color, boolean outline){
        super.size = size;      
        super.color = color;
        super.outline = outline;        
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        g2d.setColor(Color.BLACK);        
        g2d.drawRect((int) x, (int) y, (int) size,  (int) size);
    }
    
    @Override
    public void fill(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        g2d.setColor(color);
        g2d.fillRect((int) x, (int) y, (int) size,  (int) size);        
    }
    
}
