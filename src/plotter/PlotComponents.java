package plotter;

import java.awt.Color;
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
import plotter.Shapes.*;

/**
 *
 * @author benjamin
 */
public class PlotComponents {
    
    public static class Legend extends JPanel{
        private ArrayList<PlotComponents.DataSeries> series;
        private int x;
        private int y;
        private Font font;
        
        Legend(){
        }
        
        Legend(ArrayList<PlotComponents.DataSeries> series, int x, int y, Font font){
            this.series = series;
            this.x = x;
            this.y = y;
            this.font = font;
        }

        public void setLegend(ArrayList<PlotComponents.DataSeries> series, int x, int y, Font font){
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

            // Background
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,100,40*(series.size()-1)+60);
            
            Shape shape = null;
            for(int i=0; i < series.size(); i++){
                if(series.get(i).drawMarker){
                    switch(series.get(i).markerShape){
                        case "CIRCLE":
                            shape = new Ellipse2D.Double(10,40+40*i-series.get(i).markerSize, series.get(i).markerSize,series.get(i).markerSize);
                            break;
                        case "SQUARE":
                            shape = new Rectangle(10,40+40*i-series.get(i).markerSize, series.get(i).markerSize,series.get(i).markerSize);
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
                
                g2d.drawString(series.get(i).getName(),40,40+40*i); // write the name

            }   

            Coordinates legendLocation = new Coordinates(x,y);
           
            setBounds(legendLocation.getxMap(),legendLocation.getyMap(),100,40*(series.size()-1)+60);
            setBorder(BorderFactory.createLineBorder(Color.black)); // add a border to the panel
            setOpaque(false);
            
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

    public static class DataSeries extends JPanel{ // this contains the information about the dataSeries

        private String name;
        private xyData data;
        private boolean drawMarker = false;
        private int markerSize;
        private Color markerColor;
        private String markerShape;
        private boolean drawMarkerOutline = false;
        private boolean drawLine = false;
        private Color lineColor;

        DataSeries(){
        }
        
        DataSeries(double data[][]){
            this.data = new xyData(data);
        }

        DataSeries(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine, Color lineColor){   
            this.name = name;
            this.data = new xyData(data);
            this.drawMarker = drawMarker;
            this.markerSize = markerSize;
            this.markerColor = markerColor;
            this.markerShape = markerShape;
            this.drawMarkerOutline = drawMarkerOutline;          
            this.drawLine = drawLine;
            this.lineColor = lineColor;
        }
        
        public void setDataPoints(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine, Color lineColor){   
            this.name = name;
            this.data = new xyData(data);          
            this.drawMarker = drawMarker;
            this.markerSize = markerSize;
            this.markerColor = markerColor;
            this.markerShape = markerShape;
            this.drawMarkerOutline = drawMarkerOutline;           
            this.drawLine = drawLine;  
            this.lineColor = lineColor;
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
            if(drawLine) addLines(g);
            if(drawMarker) addMarkers(g);
        }
        
        public void addMarkers(Graphics g){
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // need to add some more shapes

            Shape shape;
            
            for(Coordinates point : data.getData()){
                switch (markerShape) {
                    case "CIRCLE":
                        shape = new Ellipse2D.Double(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                         break;
                    case "SQUARE":
                        shape = new Rectangle(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                        break;
                    default:
                        shape = null;
                        break;
                }
                g2d.setColor(markerColor);
                g2d.fill(shape); 
                if( drawMarkerOutline){
                    g2d.setColor(Color.BLACK);
                    g2d.draw(shape);
                }
            }
        }
        
        public void addLines(Graphics g){
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(lineColor);
            
            for(int i = 1; i < data.getData().length; i++){
                StraightLine plotLine = new StraightLine(data.getDataPoint(i-1), data.getDataPoint(i));
                g2d.drawLine(plotLine.getStart().getxMap(), plotLine.getStart().getyMap(), plotLine.getEnd().getxMap(), plotLine.getEnd().getyMap());                
            }
            
        }
        
    }
    
}
