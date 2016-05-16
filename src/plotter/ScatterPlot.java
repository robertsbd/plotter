package plotter;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */

public class ScatterPlot extends JPanel{

    private final int width;
    private final int height;
    private final int pad; // internal padding of the plot panels - the padded area is used for printing titles, labels and so forth
    private final int xMin;
    private final int xMax;
    private final int yMin;
    private final int yMax;
    private double data[][];
    
    // These are the components of the scatterplot
    private PlotComponents.DataPoints dataPoints;   
    private PlotComponents.GridLines gridLines;
    private PlotComponents.Axes axes;
    
    ScatterPlot(double data[][], int xMin, int xMax, int yMin, int yMax, int width, int height, int padding){      
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        this.pad = padding;
        this.data = data;
        dataPoints = new PlotComponents.DataPoints();
        gridLines = new PlotComponents.GridLines(xMin, xMax, yMin, yMax);         
        axes = new PlotComponents.Axes(xMin, xMax, yMin, yMax);
        new Coordinates(this.xMin, this.xMax, this.yMin, this.yMax, this.width, this.height, pad); // define the dimensions of the plotting space        
        makeScatterPlot();
    }            
     
    private void makeScatterPlot(){
        
        JLayeredPane panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(this.width,this.height));
        
        // Set the datapoints
        dataPoints.setBounds(0,0,this.width, this.height);
        dataPoints.setOpaque(false);
        panel.add(dataPoints, new Integer(0));
        
        // Set gridlines
        gridLines.setBounds(0,0,this.width, this.height);
        gridLines.setOpaque(false);
        panel.add(gridLines, new Integer(1)); // add out x and y axis with lables        
        
        // Set the axes
        axes.setBounds(0,0,this.width, this.height);
        axes.setOpaque(false);
        panel.add(axes, new Integer(2)); // add out x and y axis with lables
        add(panel);
    }

    /**
     * @param series
     */
    public void setDataPoints(int pointSize, Color pointColor, String pointShape, double data[][]) {
        this.dataPoints.setDataPoints(pointSize, pointColor, pointShape, data);             
    }
    
    /**
     * @param gridLines the gridLines to set
     */
    public void setGridLines(int numXLines, int numYLines, Color col) {
        this.gridLines.setGridLines(numXLines, numYLines, col);
    }

    /**
     * @param axes the axes to set
     */
    public void setAxes(int numXLabels, int numYLabels, int baselineXadjust, int baselineYadjust) {
        this.axes.setAxes(numXLabels, numYLabels, baselineXadjust, baselineYadjust);
    }
}
