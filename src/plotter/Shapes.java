package plotter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author benjamin
 */
public class Shapes {
    
    public static class StraightLine {
    
        private Coordinates start;
        private Coordinates end;

        StraightLine(Coordinates start, Coordinates end){

            this.start = start;
            this.end = end;

        }

        /**
         * @return the start
         */
        public Coordinates getStart() {
            return start;
        }

        /**
         * @param start the start to set
         */
        public void setStart(Coordinates start) {
            this.start = start;
        }

        /**
         * @return the end
         */
        public Coordinates getEnd() {
            return end;
        }

        /**
         * @param end the end to set
         */
        public void setEnd(Coordinates end) {
            this.end = end;
        }
    }
    
    public static class MyTriangle
    {
        protected int height;
        protected int width;
        protected Color color;

        public MyTriangle(int width, int height, Color color)
        {
            this.height = height;
            this.width= width;
            this.color= color;
        }

        public void drawMe(Graphics g, Coordinates location)
        {
            g.setColor(color);
            Coordinates point2 = new Coordinates(location.getxMap()+width,location.getyMap());
            Coordinates point3 = new Coordinates(location.getxMap()+(width/2),location.getyMap() - height);
            g.drawLine(location.getxMap(),location.getyMap(),point2.getxMap(),point2.getyMap());
            g.drawLine(location.getxMap(),location.getyMap(),point3.getxMap(),point3.getyMap());
            g.drawLine(point2.getxMap(),point2.getyMap(),point3.getxMap(),point3.getyMap());
        }
    }
            
    
}
