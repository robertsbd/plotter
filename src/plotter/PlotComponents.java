package plotter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */
public class PlotComponents {
     
    public static class Title extends JPanel{ // draws a title, can be used for writing axis titles also. Will also write 
        private String title;
        private Font font;
        private String location; // which location (top, left, right, bottom)
        private int distanceFromFrame; // a negative int that will define how far from the plotting area the title should be located.
        private float proportionUpSide;
        
        Title(){
            this.title = "";
            this.location = "TOP";
            this.distanceFromFrame = 0;
            this.font = null;            
        }
        
        Title(String title, String location, int distanceFromFrame, float proprotionUpSide, Font font){
            this.title = title;
            this.location = location;
            this.distanceFromFrame = distanceFromFrame;
            this.font = font;
        }  
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();
            Coordinates titleCoord = new Coordinates();
  
            g2d.setFont(font);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            switch (location){
                case "TOP":
                    titleCoord = new Coordinates((titleCoord.getxMax()-titleCoord.getxMin())*proportionUpSide, titleCoord.getyMax());
                    g2d.drawString(title, titleCoord.getxMap(), titleCoord.getyMap()+distanceFromFrame);
                    break;
                case "BOTTOM":
                    titleCoord = new Coordinates((titleCoord.getxMax()-titleCoord.getxMin())*proportionUpSide, titleCoord.getyMin());
                    g2d.drawString(title, titleCoord.getxMap(), titleCoord.getyMap()-distanceFromFrame);
                    break;
                case "LEFT":
                    titleCoord = new Coordinates(titleCoord.getxMin(),(titleCoord.getyMax()-titleCoord.getyMin())*proportionUpSide);
                    g2d.drawString(title, titleCoord.getxMap()+distanceFromFrame, titleCoord.getyMap());
                    break;
                case "RIGHT":
                    titleCoord = new Coordinates(titleCoord.getxMin(),(titleCoord.getyMax()-titleCoord.getyMax())*proportionUpSide);
                    g2d.drawString(title, titleCoord.getxMap()-distanceFromFrame, titleCoord.getyMap());
                    break;
            }
        }
        
        public void setTitle(String title, String location, int distanceFromFrame, float proportionUpSide, Font font){
            this.title = title;
            this.location = location;
            this.distanceFromFrame = distanceFromFrame;
            this.font = font;
            this.proportionUpSide = proportionUpSide;
        }  
        
    }
    
    public static class Axes extends JPanel{ // this will draw the axes providing the basic layout

        private int numXLabels;
        private int numYLabels;
        private int baselineXadjust; // these two should be negative number to determine how far we adjust these numbers
        private int baselineYadjust;
        private int xMin;
        private int xMax;
        private int yMin;
        private int yMax;
        private Font font;

        Axes(){
        }

        Axes(int xMin, int xMax, int yMin, int yMax){
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;        
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();

            StraightLine xAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMax, yMin));
            StraightLine yAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMin, yMax));  

            // draw the axes
            g2d.drawLine(xAxis.getStart().getxMap(), xAxis.getStart().getyMap(), xAxis.getEnd().getxMap(), xAxis.getEnd().getyMap());
            g2d.drawLine(yAxis.getStart().getxMap(), yAxis.getStart().getyMap(), yAxis.getEnd().getxMap(), yAxis.getEnd().getyMap());
            
            g2d.setFont(font);                    
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // x-axis labels
            for(int i = 0; i < numXLabels; i++){
                Coordinates labelCoordinates = new Coordinates((int) (((float) i/(numXLabels-1))*(xMax-xMin)),0);
                g2d.drawString(Integer.toString((int) labelCoordinates.getX()),labelCoordinates.getxMap(), labelCoordinates.getyMap()-baselineXadjust); 
            } 
            // y-axis labels
            for(int i = 0; i < numYLabels; i++){
                Coordinates labelCoordinates = new Coordinates(0, (int) (((float) i/(numYLabels-1))*(yMax-yMin)));
                g2d.drawString(Integer.toString((int) labelCoordinates.getY()),labelCoordinates.getxMap()+baselineYadjust, labelCoordinates.getyMap()); 
            }                       
        }
        
        public void setAxes(int numXLabels, int numYLabels, int baselineXadjust, int baselineYadjust, Font font){
            this.numXLabels = numXLabels;
            this.numYLabels = numYLabels;
            this.baselineXadjust = baselineXadjust;
            this.baselineYadjust = baselineYadjust;    
            this.font = font;
        }     
    }    

    public static class GridLines extends JPanel{

        // this panel should be behind the labels, but above the data.

        private int numXLines;
        private int numYLines;
        private Color col;
        private int xMax;
        private int xMin;
        private int yMax;
        private int yMin;

        GridLines(){
        }

        GridLines(int xMin, int xMax, int yMin, int yMax){
            this.xMax = xMax;
            this.xMin = xMin;
            this.yMax = yMax;
            this.yMin = yMin;
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(this.col);

            for(int i = 0; i < numXLines; i++){
                StraightLine gridLine = new StraightLine(new Coordinates((int) (((float) i/(numXLines-1))*(xMax-xMin)),0), new Coordinates((int) (((float) i/(numXLines-1))*(xMax-xMin)),yMax));
                g.drawLine(gridLine.getStart().getxMap(), gridLine.getStart().getyMap(), gridLine.getEnd().getxMap(), gridLine.getEnd().getyMap());
            }

            for(int i = 0; i < numYLines; i++){
                StraightLine gridLine = new StraightLine(new Coordinates(0, (int) (((float) i/(numYLines-1))*(yMax-yMin))), new Coordinates(xMax, (int) (((float) i/(numYLines-1))*(yMax-xMin))));
                g.drawLine(gridLine.getStart().getxMap(), gridLine.getStart().getyMap(), gridLine.getEnd().getxMap(), gridLine.getEnd().getyMap());
            }
        }

        public void setGridLines(int numXLines, int numYLines, Color col){
            this.numXLines = numXLines;
            this.numYLines = numYLines;
            this.col = col;
        }

    }

    public static class DataPoints extends JPanel{ // this will draw the data points

        private String name;
        private Data data;
        private int pointSize;
        private Color pointColor;
        private String pointShape;
        private boolean outline = false;

        DataPoints(){
        }

        DataPoints(int pointSize, Color pointColor, String pointShape, boolean outline, double data[][]){   
             this.pointSize = pointSize;
             this.pointShape = pointShape;
             this.pointColor = pointColor;
             this.data = new Data(data);
             this.outline = outline;
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            switch (pointShape) {
                case "CIRCLE":
                    for (Coordinates point : data.getData()) g.fillOval(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                    if(outline){ 
                        for (Coordinates point : data.getData()){
                            Shape shape = new Ellipse2D.Double(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                            g2d.setColor(this.pointColor);
                            g2d.fill(shape);     
                            g2d.setColor(Color.BLACK);
                            g2d.draw(shape);
                        }
                    }
                    break;
                case "SQUARE":
                    for (Coordinates point : data.getData()) g.fillRect(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                    if(outline){ 
                        for (Coordinates point : data.getData()){
                            Shape shape = new Rectangle(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                            g2d.setColor(this.pointColor);
                            g2d.fill(shape); 
                            g2d.setColor(Color.BLACK);
                            g2d.draw(shape);
                        }
                    }
                    break;
            }
        } 

        public void setDataPoints(String name, int pointSize, Color pointColor, String pointShape, boolean outline, double data[][]){   
            this.name = name;
            this.pointSize = pointSize;
            this.pointShape = pointShape;
            this.pointColor = pointColor;
            this.outline = outline;
            this.data = new Data(data);
        }
        
    }
    
}
