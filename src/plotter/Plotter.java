
package plotter;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import plotter.PlotComponents.*;

/**
 *
 * @author benjamin
 */
public class Plotter{

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    
    public static void main(String[] args) {
        
        double[][] myData = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData[i][0] = 20*i;
            myData[i][1] = Math.random()*100;
        }

        double[][] myData2 = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData2[i][0] = 20*i;
            myData2[i][1] = Math.random()*300+50;
        }

        JFrame frame = new JFrame("Scatterplot");
        
        ScatterPlot scatterPlot;
        
        // we need to add the data to the scatterplot when we create it otherwise it doesn't know if there is data or not to plot.
        
        ArrayList<DataSeries> allData;
        allData = new ArrayList<>();
        allData.add(new DataSeries(myData));
        allData.add(new DataSeries(myData2));       
        
        scatterPlot = new ScatterPlot(allData, 0, 400, 0, 350, WIDTH, HEIGHT, 140);
        scatterPlot.data.get(0).setDataPoints("series1", myData, true, 10, Color.PINK,"SQUARE", true, true, Color.PINK);
        scatterPlot.data.get(1).setDataPoints("series2", myData2, true, 10, Color.CYAN,"CIRCLE", true, true, Color.BLUE);
        scatterPlot.gridLines.setGridLines(11,15,new Color(200,200,200));
        scatterPlot.axes.setAxes(6,8,-30,-40, new Font("Arial", Font.PLAIN, 12));
        scatterPlot.title.setTitle("Mass as a function of time", "TOP", -40, (float) 0, new Font("Arial", Font.PLAIN, 25));
        scatterPlot.xTitle.setTitle("time (sec)", "BOTTOM", -80, (float) .46, new Font("Arial", Font.PLAIN, 18));
        scatterPlot.yTitle.setTitle("mass", "LEFT", -110, (float) .5, new Font("Arial", Font.PLAIN, 18));
        scatterPlot.legend.setLegend(scatterPlot.data,410,350, new Font("Arial", Font.PLAIN, 14));
        
        frame.setSize(WIDTH, HEIGHT);
        frame.add(scatterPlot);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
    }

}