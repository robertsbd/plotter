package plotcomponents;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import data.Coordinates;
import graphics.StraightLine;

/**
 *
 * @author benjamin
 */
public class Axes extends JPanel{ // this will draw the axes providing the basic layout

    private int numXLabels;
    private int numYLabels;
    private int baselineXadjust; // these two should be negative number to determine how far we adjust these numbers
    private int baselineYadjust;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private Font font;

    public Axes(){
    }

    public Axes(int xMin, int xMax, int yMin, int yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;        
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();

        StraightLine xAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMax, yMin));
        StraightLine yAxis = new StraightLine(new Coordinates(xMin,yMin), new Coordinates(xMin, yMax));  

        // draw the axes
        g2d.drawLine(xAxis.getStart().getxMap(), xAxis.getStart().getyMap(), xAxis.getEnd().getxMap(), xAxis.getEnd().getyMap());
        g2d.drawLine(yAxis.getStart().getxMap(), yAxis.getStart().getyMap(), yAxis.getEnd().getxMap(), yAxis.getEnd().getyMap());

        g2d.setFont(font);                    
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // x-axis labels
        for(int i = 0; i < numXLabels; i++){
            Coordinates labelCoordinates = new Coordinates((int) (((float) i/(numXLabels-1))*(xMax-xMin)),0);
            g2d.drawString(Integer.toString((int) labelCoordinates.getX()),labelCoordinates.getxMap(), labelCoordinates.getyMap()-baselineXadjust); 
        } 
        // y-axis labels
        for(int i = 0; i < numYLabels; i++){
            Coordinates labelCoordinates = new Coordinates(0, (int) (((float) i/(numYLabels-1))*(yMax-yMin)));
            g2d.drawString(Integer.toString((int) labelCoordinates.getY()),labelCoordinates.getxMap()+baselineYadjust, labelCoordinates.getyMap()); 
        }                       
    }

    public void setAxes(int numXLabels, int numYLabels, int baselineXadjust, int baselineYadjust, Font font){
        this.numXLabels = numXLabels;
        this.numYLabels = numYLabels;
        this.baselineXadjust = baselineXadjust;
        this.baselineYadjust = baselineYadjust;    
        this.font = font;
    }     
} 