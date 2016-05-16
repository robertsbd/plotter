package plotter;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */
public class PlotComponents {
        
    public static class Title extends JPanel{
        private String title;
        private int fontSize;
        
        Title(){
        }
        
        Title(String title){
            this.title = title;
            
        }    
    }
    
    public static class xAxisTitle extends JPanel{
        private String title;
        private int fontSize;
        
        xAxisTitle(){
        }
        
        xAxisTitle(String title){
            this.title = title;
            
        }    
    }

    public static class yAxisTitle extends JPanel{
        private String title;
        private int fontSize;
        
        yAxisTitle(){
        }
        
        yAxisTitle(String title){
            this.title = title;
            
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

            StraightLine xAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMax, yMin));
            StraightLine yAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMin, yMax));  

            // draw the axes
            g.drawLine(xAxis.getStart().getxMap(), xAxis.getStart().getyMap(), xAxis.getEnd().getxMap(), xAxis.getEnd().getyMap());
            g.drawLine(yAxis.getStart().getxMap(), yAxis.getStart().getyMap(), yAxis.getEnd().getxMap(), yAxis.getEnd().getyMap());
            // x-axis labels
            for(int i = 0; i < numXLabels; i++){
                Coordinates labelCoordinates = new Coordinates((int) (((float) i/(numXLabels-1))*(xMax-xMin)),0);
                g.drawString(Integer.toString((int) labelCoordinates.getX()),labelCoordinates.getxMap(), labelCoordinates.getyMap()-baselineXadjust); 
            } 
            // y-axis labels
            for(int i = 0; i < numYLabels; i++){
                Coordinates labelCoordinates = new Coordinates(0, (int) (((float) i/(numYLabels-1))*(yMax-yMin)));
                g.drawString(Integer.toString((int) labelCoordinates.getY()),labelCoordinates.getxMap()+baselineYadjust, labelCoordinates.getyMap()); 
            }                       
        }
        
        public void setAxes(int numXLabels, int numYLabels, int baselineXadjust, int baselineYadjust){
            this.numXLabels = numXLabels;
            this.numYLabels = numYLabels;
            this.baselineXadjust = baselineXadjust;
            this.baselineYadjust = baselineYadjust;    
        }     

        /**
         * @param numXLabels the numXLabels to set
         */
        public void setNumXLabels(int numXLabels) {
            this.numXLabels = numXLabels;
        }

        /**
         * @param numYLabels the numYLabels to set
         */
        public void setNumYLabels(int numYLabels) {
            this.numYLabels = numYLabels;
        }

        /**
         * @param baselineXadjust the baselineXadjust to set
         */
        public void setBaselineXadjust(int baselineXadjust) {
            this.baselineXadjust = baselineXadjust;
        }

        /**
         * @param baselineYadjust the baselineYadjust to set
         */
        public void setBaselineYadjust(int baselineYadjust) {
            this.baselineYadjust = baselineYadjust;
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
        
        /**
         * @param numXLines the numXLines to set
         */
        public void setNumXLines(int numXLines) {
            this.numXLines = numXLines;
        }

        /**
         * @param numYLines the numYLines to set
         */
        public void setNumYLines(int numXLines) {
            this.numYLines = numYLines;
        }

        /**
         * @param col the col to set
         */
        public void setCol(Color col) {
            this.col = col;
        }

    }

    public static class DataPoints extends JPanel{ // this will draw the data points

        private Data data;
        private int pointSize;
        private Color pointColor;
        private String pointShape;

        DataPoints(){
        }

        DataPoints(int pointSize, Color pointColor, String pointShape, double data[][]){   
             this.pointSize = pointSize;
             this.pointShape = pointShape;
             this.pointColor = pointColor;
             this.data = new Data(data);
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(this.pointColor);
            switch (pointShape) {
                case "CIRCLE":
                    for (Coordinates point : data.getData()) g.fillOval(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                    break;
                case "SQUARE":
                    for (Coordinates point : data.getData()) g.fillRect(point.getxMap()-pointSize/2, point.getyMap()-pointSize/2, pointSize, pointSize);
                    break;
            }
        } 

        public void setDataPoints(int pointSize, Color pointColor, String pointShape, double data[][]){   
            this.pointSize = pointSize;
            this.pointShape = pointShape;
            this.pointColor = pointColor;
            this.data = new Data(data);
        }
        
    }
    
}
