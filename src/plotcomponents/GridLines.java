package plotcomponents;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import data.Coordinates;
import graphics.StraightLine;

/**
 *
 * @author benjamin
 */
public class GridLines extends JPanel{

     // this panel should be behind the labels, but above the data.

     private int numXLines;
     private int numYLines;
     private Color col;
     private int xMax;
     private int xMin;
     private int yMax;
     private int yMin;

     public GridLines(){
     }

     public GridLines(int xMin, int xMax, int yMin, int yMax){
         this.xMax = xMax;
         this.xMin = xMin;
         this.yMax = yMax;
         this.yMin = yMin;
     }

     @Override
     public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.setColor(this.col);

         for(int i = 0; i < numXLines; i++){
             StraightLine gridLine = new StraightLine(new Coordinates((int) (((float) i/(numXLines-1))*(xMax-xMin)),0), new Coordinates((int) (((float) i/(numXLines-1))*(xMax-xMin)),yMax));
             g.drawLine(gridLine.getStart().getxMap(), gridLine.getStart().getyMap(), gridLine.getEnd().getxMap(), gridLine.getEnd().getyMap());
         }

         for(int i = 0; i < numYLines; i++){
             StraightLine gridLine = new StraightLine(new Coordinates(0, (int) (((float) i/(numYLines-1))*(yMax-yMin))), new Coordinates(xMax, (int) (((float) i/(numYLines-1))*(yMax-xMin))));
             g.drawLine(gridLine.getStart().getxMap(), gridLine.getStart().getyMap(), gridLine.getEnd().getxMap(), gridLine.getEnd().getyMap());
         }
     }

     public void setGridLines(int numXLines, int numYLines, Color col){
         this.numXLines = numXLines;
         this.numYLines = numYLines;
         this.col = col;
     }

 }
