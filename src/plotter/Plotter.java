
package plotter;

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
        
        JFrame frame = new JFrame("Scatterplot");
        ScatterPlot scatterPlot = new ScatterPlot(myData, 0, 500, 0, 500, WIDTH, HEIGHT, 200);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(scatterPlot);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
    }

}
