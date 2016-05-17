package plotter;

import data.Coordinates;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import plotcomponents.*;

// Note the scatterplot can also be used for line graphs so we don't need these two types.

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
    
    // These are the components of the scatterplot
    protected ArrayList<DataSeries> data;   
    protected GridLines gridLines;
    protected Axes axes;
    protected Title title;
    protected Title xTitle;
    protected Title yTitle;
    protected Legend legend;

    private JPanel background;
    
    ScatterPlot(ArrayList<DataSeries> data, int xMin, int xMax, int yMin, int yMax, int width, int height, int padding){      
        this.data = data;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        this.pad = padding;

        gridLines = new GridLines(xMin, xMax, yMin, yMax);         
        axes = new Axes(xMin, xMax, yMin, yMax);
        title = new Title();
        xTitle = new Title();
        yTitle = new Title();
        legend = new Legend();
        new Coordinates(this.xMin, this.xMax, this.yMin, this.yMax, this.width, this.height, pad); // define the dimensions of the plotting space        
        makeScatterPlot();
    }            
     
    private void makeScatterPlot(){
        
        JLayeredPane panel = new JLayeredPane();
        panel.setPreferredSize(new Dimension(this.width,this.height));
        
        // first a background
        background = new JPanel();
        background.setBounds(0,0,this.width, this.height);
        background.setBackground(Color.WHITE);
        panel.add(background, new Integer(-1));
        
        // Set gridlines
        gridLines.setBounds(0,0,this.width, this.height);
        gridLines.setOpaque(false);
        panel.add(gridLines, new Integer(0)); // add out x and y axis with lables        
        
        // Set the axes
        axes.setBounds(0,0,this.width, this.height);
        axes.setOpaque(false);
        panel.add(axes, new Integer(1)); // add out x and y axis with lables
        
        // Plot the datapoints
        int i = 0;
        for(i = 0; i < data.size(); i++){
                data.get(i).setBounds(0,0,this.width, this.height);
                data.get(i).setOpaque(false);
                panel.add(data.get(i), new Integer(2+i));
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
        
        legend.setBounds(0,0,this.width,this.height); 
        legend.setOpaque(false);
        panel.add(legend, new Integer(6+i));       
        add(panel);
    }
}
