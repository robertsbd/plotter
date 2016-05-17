package plotter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */
public class PlotComponents {
    
    public static class Legend extends JPanel{
        private ArrayList<PlotComponents.DataPoints> series;
        private int x;
        private int y;
        private Font font;
        
        Legend(){
        }
        
        Legend(ArrayList<PlotComponents.DataPoints> series, int x, int y, Font font){
            this.series = series;
            this.x = x;
            this.y = y;
            this.font = font;
        }

        public void setLegend(ArrayList<PlotComponents.DataPoints> series, int x, int y, Font font){
            this.series = series;
            this.x = x;
            this.y = y;
            this.font = font;
        }
        
        // need to scale x and y values os they make sense in terms of the rest of the coordinates scheme
        // need some kind of out of bounds messages when the user tries to plot things that are out of the bounds of the image
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();              
            
            Coordinates legendCoord = new Coordinates(x, y);

            g2d.setFont(font);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

// would be better to draw all of this with a layout manager. But for the moment just paint it on with x and y.
          
            Shape shape = null;
            for(int i=0; i < series.size(); i++){
                if(series.get(i).drawMarker){
                    switch(series.get(i).markerShape){
                        case "CIRCLE":
                            shape = new Ellipse2D.Double(legendCoord.getxMap(),legendCoord.getyMap()+40*i-series.get(i).markerSize, series.get(i).markerSize,series.get(i).markerSize);
                            break;
                        case "SQUARE":
                            shape = new Rectangle(legendCoord.getxMap(),legendCoord.getyMap()+40*i-series.get(i).markerSize, series.get(i).markerSize,series.get(i).markerSize);
                            break;
                        default:
                            break;
                    }
                }
                g2d.setColor(series.get(i).markerColor); // fill our marker
                g2d.fill(shape);     
                
                if(series.get(i).drawMarkerOutline){              // outline our marker
                    g2d.setColor(Color.BLACK);
                    g2d.draw(shape);
                }
                
                g2d.drawString(series.get(i).getName(),legendCoord.getxMap()+40,legendCoord.getyMap()+40*i); // write the name

            }   

            setSize(1000,1000);
            setBorder(BorderFactory.createLineBorder(Color.black)); // add a border to the panel
        }
    }
     
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
        private boolean drawMarker = false;
        private int markerSize;
        private Color markerColor;
        private String markerShape;
        private boolean drawMarkerOutline = false;
        private boolean drawLine = false;

        DataPoints(){
        }

        DataPoints(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine){   
            this.name = name;
            this.data = new Data(data);
            this.drawMarker = drawMarker;
            this.markerSize = markerSize;
            this.markerColor = markerColor;
            this.markerShape = markerShape;
            this.drawMarkerOutline = drawMarkerOutline;          
            this.drawLine = drawLine;      
        }
        
        public void setDataPoints(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine){   
            this.name = name;
            this.data = new Data(data);          
            this.drawMarker = drawMarker;
            this.markerSize = markerSize;
            this.markerColor = markerColor;
            this.markerShape = markerShape;
            this.drawMarkerOutline = drawMarkerOutline;           
            this.drawLine = drawLine;     
        }
        /**
         * @return the name
         */
        @Override
        public String getName() {
            return name;
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            switch (markerShape) {
                case "CIRCLE":
                    for (Coordinates point : data.getData()) g.fillOval(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                    if(drawMarkerOutline){ 
                        for (Coordinates point : data.getData()){
                            Shape shape = new Ellipse2D.Double(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                            g2d.setColor(this.markerColor);
                            g2d.fill(shape);     
                            g2d.setColor(Color.BLACK);
                            g2d.draw(shape);
                        }
                    }
                    break;
                case "SQUARE":
                    for (Coordinates point : data.getData()) g.fillRect(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                    if(drawMarkerOutline){ 
                        for (Coordinates point : data.getData()){
                            Shape shape = new Rectangle(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                            g2d.setColor(this.markerColor);
                            g2d.fill(shape); 
                            g2d.setColor(Color.BLACK);
                            g2d.draw(shape);
                        }
                    }
                    break;
                default:
                    break;
            }
        } 
        
    }
    
}
