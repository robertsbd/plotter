
package plotter;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import plotter.PlotComponents.*;

/**
 *
 * @author benjamin
 */
public class Plotter{

    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    public static void main(String[] args) {
        
        double[][] myData;
        double[][] myData2;
                
        myData = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData[i][0] = 20*i;
            myData[i][1] = Math.random()*10 + 100;
        }

        myData2 = new double[20][2];
        
        for(int i = 0; i < 20; i++){
            myData2[i][0] = 20*i;
            myData2[i][1] = Math.random()*10+300*i;
        }

        JFrame frame = new JFrame("Scatterplot");
          
        ArrayList<DataSeries> allData;
        allData = new ArrayList<>();
        allData.add(new DataSeries(myData));
        allData.add(new DataSeries(myData2));       
        
        ScatterPlot scatterPlot = new ScatterPlot(allData, 0, 400, 0, 350, WIDTH, HEIGHT, 120);
        scatterPlot.data.get(0).setDataPoints("Control", myData, true, 10, Color.PINK,"SQUARE", true, true, Color.PINK);
        scatterPlot.data.get(1).setDataPoints("Phobic", myData2, true, 10, Color.CYAN,"CIRCLE", true, true, Color.BLUE);
        scatterPlot.gridLines.setGridLines(11,15,new Color(200,200,200));
        scatterPlot.axes.setAxes(6,8,-30,-40, new Font("Arial", Font.PLAIN, 12));
        scatterPlot.title.setTitle("Reported anxiety as a function of dosage", "TOP", -40, (float) 0, new Font("Arial", Font.PLAIN, 20));
        scatterPlot.xTitle.setTitle("dose (mg)", "BOTTOM", -70, (float) .46, new Font("Arial", Font.PLAIN, 15));
        scatterPlot.yTitle.setTitle("anxiety", "LEFT", -100, (float) .5, new Font("Arial", Font.PLAIN, 15));
        scatterPlot.legend.setLegend(scatterPlot.data,410,350, new Font("Arial", Font.PLAIN, 14));
        
        frame.setSize(WIDTH, HEIGHT);
        frame.add(scatterPlot);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
        
        while(true){
            myData = new double[20][2];

            for(int i = 0; i < 20; i++){
                myData[i][0] = 20*i;
                myData[i][1] = Math.random()*10+100;
            }
            
            myData2 = new double[20][2];
 
            for(int i = 0; i < 20; i++){
                myData2[i][0] = 20*i;
                myData2[i][1] = Math.random()*20-8*i+200;
            }  
 

            scatterPlot.data.get(0).setDataPoints("Control", myData, true, 10, Color.PINK,"SQUARE", true, true, Color.PINK);
            scatterPlot.data.get(1).setDataPoints("Phobic", myData2, true, 10, Color.CYAN,"CIRCLE", true, true, Color.BLUE);            

            frame.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Plotter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}