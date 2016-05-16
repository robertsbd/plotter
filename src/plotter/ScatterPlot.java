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
    
    ScatterPlot(double data[][], int xMin, int xMax, int yMin, int yMax, int width, int height, int padding){
        
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        this.pad = padding;
        this.data = data;
        makeScatterPlot();
    }            
     
    private void makeScatterPlot(){
        
        new Coordinates(this.xMin, this.xMax, this.yMin, this.yMax, this.width, this.height, pad); // define the dimensions of the plotting space
        
        JLayeredPane panel = new JLayeredPane();
        
        panel.setPreferredSize(new Dimension(this.width,this.height));
        
        // Create the datapoints
        PlotComponents.DataPoints series1 = new PlotComponents.DataPoints(20, Color.PINK,"SQUARE", data);
        series1.setBounds(0,0,this.width, this.height);
        panel.add(series1, new Integer(0));
        series1.setOpaque(false);
        
        // Create gridlines
        PlotComponents.GridLines gridLines = new PlotComponents.GridLines(xMin, xMax, yMin, yMax);
        gridLines.setNumXLines(11);
        gridLines.setNumYLines(11);
        gridLines.setBounds(0,0,this.width, this.height);
        gridLines.setOpaque(false);
        panel.add(gridLines, new Integer(1)); // add out x and y axis with lables        
        
        // Create the axes
        PlotComponents.Axes axes = new PlotComponents.Axes(xMin, xMax, yMin, yMax);
        axes.setNumXLabels(6);
        axes.setNumYLabels(6);
        axes.setBaselineXadjust(-30);
        axes.setBaselineYadjust(-40);
        axes.setBounds(0,0,this.width, this.height);
        axes.setOpaque(false);
        panel.add(axes, new Integer(2)); // add out x and y axis with lables
        add(panel);
    }
}
