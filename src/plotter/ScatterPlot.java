package plotter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author benjamin
 */

public class ScatterPlot extends JFrame{

    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 800;
    static final int PLOT_WIDTH = 700;
    static final int PLOT_HEIGHT = 700;
    
    ScatterPlot(String title){
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle(title);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        DrawScatterPlot scatterPlotDrawing = new DrawScatterPlot();
        panel.add(scatterPlotDrawing);
        add(panel);
        setVisible(true);
    }
    
    public class DrawScatterPlot extends JPanel{

        private StraightLine xAxis;
        private StraightLine yAxis;
        private int numberLabels = 11;
        
        DrawScatterPlot(){
            JPanel plotPanel = new JPanel();
            setPreferredSize(new Dimension (PLOT_WIDTH+50,PLOT_HEIGHT+50)); // this bodgy addition is so that we have some extra space around the chart on the right hand side and bottom
            // need to just define the plot area, it will be 20 less than the size of the frame
            getAxes();            
            
        }
        
        public void getAxes(){ // create the axes
            xAxis = new StraightLine(new Coordinates(-1*(PLOT_HEIGHT/2),0), new Coordinates(PLOT_HEIGHT/2, 0));
            yAxis = new StraightLine(new Coordinates(0,-1*(PLOT_WIDTH/2)), new Coordinates(0, PLOT_WIDTH/2));             
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            paintAxes(g);
            paintXLables(g, numberLabels);
            paintYLables(g, numberLabels);
            paintPoints(g);
        }
        
        public void paintXLables(Graphics g, int numLabels){
            for(int i = 0; i < numLabels; i++){
                int labelValToPlot = (int) (((float) i/(numLabels-1))*PLOT_WIDTH - PLOT_WIDTH/2);
                Coordinates labelCoordinates = new Coordinates(labelValToPlot,-20);
                g.drawString(Integer.toString(labelValToPlot),labelCoordinates.mapX(PLOT_WIDTH), labelCoordinates.mapY(PLOT_HEIGHT));
            }
        }
        
         public void paintYLables(Graphics g, int numLabels){
            for(int i = 0; i < numLabels; i++){
                int labelValToPlot = (int) (((float) i/(numLabels-1))*PLOT_WIDTH - PLOT_WIDTH/2);
                Coordinates labelCoordinates = new Coordinates(5, labelValToPlot-10);
                g.drawString(Integer.toString(labelValToPlot),labelCoordinates.mapX(PLOT_WIDTH), labelCoordinates.mapY(PLOT_HEIGHT));
            }
        }
        
        public void paintAxes(Graphics g){   
            drawStraightLine(g, xAxis);
            drawStraightLine(g, yAxis);
        }
        
        public void paintPoints(Graphics g){
            Coordinates data[] = new Coordinates[10]; 
            
            for(int i =0; i < 10; i++){
                data[i] = new Coordinates(i*10, i*10);
            }
            
            g.setColor(Color.RED);
            
            for(int i = 0; i < 10; i++){
                g.fillOval(data[i].mapX(PLOT_WIDTH)-4, data[i].mapY(PLOT_HEIGHT)-4, 8, 8);
            }
            
        }

        public void drawStraightLine(Graphics g, StraightLine line){

            g.drawLine(line.getStart().mapX(PLOT_WIDTH), line.getStart().mapY(PLOT_HEIGHT), line.getEnd().mapX(PLOT_WIDTH), line.getEnd().mapY(PLOT_HEIGHT));
        }
    }
}
