package shapes;

import java.awt.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author benjamin
 */
public class Triangle extends Shape// note x,y will be the bottom left corner of the triangle
    {
        private int x;
        private int y;
        private int height;
        private int width;
        private Polygon triangle;
        
        public Triangle(int x, int y, int width, int height)
        {
            int[] xVals = new int[3];
            int[] yVals = new int[3];

            this.height = height;
            this.width= width;
            this.x = x;
            this.y = y;

            xVals[0] = x;
            xVals[1] = x+width;
            xVals[2] = x+(width/2);        
            yVals[0] = y;
            yVals[1] = y;
            yVals[2] = y - height; // this will need to be changes to a plus when we start thinking about the dimensions that we will be working in.
            triangle = new Polygon(xVals, yVals, 3);
        }

        public Triangle() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

//        public void draw(Graphics g){     
//            g.drawPolygon(triangle);
//        }
//
//        public void fill(Graphics g){
//            g.fillPolygon(triangle);
//        }

        @Override
        public com.sun.javafx.geom.Shape impl_configShape() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }