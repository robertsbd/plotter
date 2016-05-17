package plotter;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
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
    private final int numDataSeries;
    
    // These are the components of the scatterplot
    protected ArrayList<PlotComponents.DataPoints> dataPoints;   
    protected PlotComponents.GridLines gridLines;
    protected PlotComponents.Axes axes;
    protected PlotComponents.Title title;
    protected PlotComponents.Title xTitle;
    protected PlotComponents.Title yTitle;
    protected PlotComponents.Legend legend;
    
    ScatterPlot(int xMin, int xMax, int yMin, int yMax, int width, int height, int padding, int numDataSeries){      
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        this.pad = padding;
        this.numDataSeries = numDataSeries;

        dataPoints = new ArrayList<>();
        gridLines = new PlotComponents.GridLines(xMin, xMax, yMin, yMax);         
        axes = new PlotComponents.Axes(xMin, xMax, yMin, yMax);
        title = new PlotComponents.Title();
        xTitle = new PlotComponents.Title();
        yTitle = new PlotComponents.Title();
        legend = new PlotComponents.Legend();
        new Coordinates(this.xMin, this.xMax, this.yMin, this.yMax, this.width, this.height, pad); // define the dimensions of the plotting space        
        makeScatterPlot();
    }            
     
    private void makeScatterPlot(){
        
        JLayeredPane panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(this.width,this.height));
        
        // Set gridlines
        gridLines.setBounds(0,0,this.width, this.height);
        gridLines.setOpaque(false);
        panel.add(gridLines, new Integer(0)); // add out x and y axis with lables        
        
        // Set the axes
        axes.setBounds(0,0,this.width, this.height);
        axes.setOpaque(false);
        panel.add(axes, new Integer(1)); // add out x and y axis with lables
        
        // Set the datapoints

        int i;
        for(i = 0; i < numDataSeries; i++){
            dataPoints.add(new PlotComponents.DataPoints());
            dataPoints.get(i).setBounds(0,0,this.width, this.height);
            dataPoints.get(i).setOpaque(false);
            panel.add(dataPoints.get(i), new Integer(2+i));
        }
            
        // set the remaining aspects of the chart
        
        title.setBounds(0,0,this.width, this.height);
        title.setOpaque(false);
        panel.add(title, new Integer(3+i));
        
        xTitle.setBounds(0,0,this.width, this.height);
        xTitle.setOpaque(false);
        panel.add(xTitle, new Integer(4+i));
        
        yTitle.setBounds(0,0,this.width, this.height);
        yTitle.setOpaque(false);
        panel.add(yTitle, new Integer(5+i));
        
        Rectangle prefSize = legend.getBounds();
        legend.setBounds(0,0,300,300);
        legend.setOpaque(false);
        panel.add(legend, new Integer(6+i));       
        
        add(panel);
    }
}
