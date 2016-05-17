package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
/**
 *
 * @author benjamin
 */
public class Triangle extends Shape{// note x,y will be the bottom left corner of the triangle
    
        private double height;
        private double width;
        private Path2D.Double triangle;

        public Triangle() {
            this.name = "TRIANGLE";
        }
        
        public Triangle(double x, double y, double width, double height)
        {

            this.height = height;
            this.width= width;
            this.x = x;
            this.y = y;
            
            triangle = new Path2D.Double();
            triangle.moveTo(x, y);
            triangle.lineTo(x+width, y);
            triangle.lineTo(x+(width/2), y-height); // check that this line still works
            triangle.closePath();
            this.name = "TRIANGLE";
        }

        @Override
        public void draw(Graphics g){     
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.draw(triangle);
            g2d.dispose();
        }

        @Override
        public void fill(Graphics g){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.fill(triangle);
            g2d.dispose();
        }
    }