package markers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
/**
 *
 * @author benjamin
 */
public class InvertedTriangle extends Marker{// note x,y will be the bottom left corner of the triangle
    
    public InvertedTriangle(){
    }

    public InvertedTriangle(double size, Color color, boolean outline){
        super.size = size;
        super.color = color;
        super.outline = outline;   
    }

    @Override
    public void draw(Graphics g){   
        Graphics2D g2d = (Graphics2D) g.create();     
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
        Path2D.Double triangle;       
        triangle = new Path2D.Double();
        triangle.moveTo(x, y);
        triangle.lineTo(x+size, y);
        triangle.lineTo(x+(size/2), y+size); // check that this line still works
        triangle.closePath();
        g2d.setColor(Color.BLACK);
        g2d.draw(triangle);
        g2d.dispose();
    }

    @Override
    public void fill(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);       
        Path2D.Double triangle;       
        triangle = new Path2D.Double();
        triangle.moveTo(x, y);
        triangle.lineTo(x+size, y);
        triangle.lineTo(x+(size/2), y+size); // check that this line still works
        triangle.closePath();
        g2d.setColor(color);
        g2d.fill(triangle);
        g2d.dispose();
    }
}