
package plotter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author benjamin
 */
public class Plotter{

    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    
    public static void main(String[] args) {
        
        double[][] myData = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData[i][0] = 20*i + Math.random()*100;
            myData[i][1] = 20*i + Math.random()*100;
        }

        double[][] myData2 = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData2[i][0] = 20*i + Math.random()*100;
            myData2[i][1] = 20*i + Math.random()*100;
        }

        JFrame frame = new JFrame("Scatterplot");
        
        ScatterPlot scatterPlot;
        scatterPlot = new ScatterPlot(0, 500, 0, 500, WIDTH, HEIGHT, 140, 2);
        scatterPlot.dataPoints.get(0).setDataPoints("series1", myData, true, 15, Color.PINK,"SQUARE", true, false);
        scatterPlot.dataPoints.get(1).setDataPoints("series2", myData2, true, 15, Color.CYAN,"CIRCLE", true, false);
        scatterPlot.gridLines.setGridLines(11,11,new Color(200,200,200));
        scatterPlot.axes.setAxes(6,6,-30,-40, new Font("Arial", Font.PLAIN, 12));
        scatterPlot.title.setTitle("Relation between X and Y", "TOP", -40, (float) 0, new Font("Arial", Font.PLAIN, 25));
        scatterPlot.xTitle.setTitle("x-axis", "BOTTOM", -80, (float) .46, new Font("Arial", Font.PLAIN, 18));
        scatterPlot.yTitle.setTitle("yaxis", "LEFT", -110, (float) .5, new Font("Arial", Font.PLAIN, 18));
        scatterPlot.legend.setLegend(scatterPlot.dataPoints,510,500, new Font("Arial", Font.PLAIN, 14));
        
        frame.setSize(WIDTH, HEIGHT);
        frame.add(scatterPlot);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
    }

}