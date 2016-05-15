package plotter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */

public class ScatterPlot extends JFrame{

    static final int F_WIDTH = 1000;
    static final int F_HEIGHT = 1000;
    static final int INT_PAD = 40; // internal padding of the frame - the padded area is used for printing titles, labels and so forth
    
    private int chartXmin = 0;
    private int chartXmax = 500;
    private int chartYmin = 0;
    private int chartYmax = 500;
    
    ScatterPlot(String title){
        setSize(F_WIDTH+INT_PAD, F_HEIGHT+INT_PAD);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle(title);
        
        new Coordinates(chartXmin, chartXmax, chartYmin, chartYmax, F_WIDTH, F_HEIGHT, INT_PAD); // define the dimensions of the plotting space

        float[][] myData = new float[100][2];
        
        for(int i = 0; i < 100; i++){
            myData[i][0] = i;
            myData[i][1] = i;
        }
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(new AxesPanel()); // add out x and y axis with lables
        panel.add(new DataPanel(2, Color.RED,"CIRCLE", myData));
        add(panel);
        setVisible(true);
    }
    
    private class DataPanel extends JPanel{ // this will draw the data points
        
        Data data;
        int pointSize;
        Color pointColor;
        String pointShape;
        
        DataPanel(int pointSize, Color pointColor, String pointShape, float data[][]){   
             this.pointSize = pointSize;
             this.pointShape = pointShape;
             this.pointColor = pointColor;
             this.data = new Data(data);
             setPreferredSize(new Dimension(F_WIDTH, F_HEIGHT));
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(this.pointColor);
            switch (pointShape) {
                case "CIRCLE":
                    for (Coordinates point : data.getData()) g.fillOval(point.getxMap()-pointSize/2, point.getyMap()+pointSize/2, pointSize, pointSize);
                    break;
                case "SQUARE":
                    for (Coordinates point : data.getData()) g.fillRect(point.getxMap()-pointSize/2, point.getyMap()+pointSize/2, pointSize, pointSize);
                    break;
            }
        }        
       
        
    }
    
    private class AxesPanel extends JPanel{ // this will draw the axes providing the basic layout

        private StraightLine xAxis;
        private StraightLine yAxis;
        private int numXLabels = 11;
        private int numYLabels = 11;

        
        AxesPanel(){
            getAxes();
            setPreferredSize(new Dimension(F_WIDTH, F_HEIGHT));
        }
        
        public void getAxes(){ // create the axes
            xAxis = new StraightLine(new Coordinates(chartXmin,chartYmin), new Coordinates(chartXmax, chartYmin)); // change these in time so axes cross at 0.
            yAxis = new StraightLine(new Coordinates(chartXmin,chartYmin), new Coordinates(chartXmin, chartYmax));             
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            paintAxes(g);
        }
        
        public void paintAxes(Graphics g){   
            g.drawLine(xAxis.getStart().getxMap(), xAxis.getStart().getyMap(), xAxis.getEnd().getxMap(), xAxis.getEnd().getyMap());
            g.drawLine(yAxis.getStart().getxMap(), yAxis.getStart().getyMap(), yAxis.getEnd().getxMap(), yAxis.getEnd().getyMap());
            
            // paint the x-axis labels
            for(int i = 0; i < numXLabels; i++){
                Coordinates labelCoordinates = new Coordinates((int) (((float) i/(numXLabels-1))*(chartXmax-chartXmin)),0);
                g.drawString(Integer.toString((int) labelCoordinates.getX()),labelCoordinates.getxMap(), labelCoordinates.getyMap()+(INT_PAD/2)); // we minus 30 to get it away from the axis
            }
            
            // paint the y-axis labels
            for(int i = 0; i < numYLabels; i++){
                Coordinates labelCoordinates = new Coordinates(0, (int) (((float) i/(numYLabels-1))*(chartYmax-chartYmin)));
                g.drawString(Integer.toString((int) labelCoordinates.getY()),labelCoordinates.getxMap()-(INT_PAD), labelCoordinates.getyMap()); // we add 30 to get it away from the axis
            }            
            
        }
    }
}
