package plotcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import data.Coordinates;

/**
 *
 * @author benjamin
 */
public class Legend extends JPanel{
     private ArrayList<DataSeries> series = new ArrayList<>();
     private int x;
     private int y;
     private Font font;

     public Legend(){
     }

     public Legend(ArrayList<DataSeries> series, int x, int y, Font font){
         this.series = series;
         this.x = x;
         this.y = y;
         this.font = font;
     }

     public void setLegend(ArrayList<DataSeries> series, int x, int y, Font font){
         this.series = series;
         this.x = x;
         this.y = y;
         this.font = font;
     }

     // need to scale x and y values os they make sense in terms of the rest of the coordinates scheme
     // need some kind of out of bounds messages when the user tries to plot things that are out of the bounds of the image

     @Override
     public void paintComponent(Graphics g){
         super.paintComponent(g);
         if(!series.isEmpty()) paintLegend(g);
     }  

     public void paintLegend(Graphics g){
         // would be better to draw all of this with a layout manager. But for the moment just paint it on with x and y.
         Shape shape = new Rectangle();

         Graphics2D g2d = (Graphics2D)g.create();   
         g2d.setFont(font);
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

         Coordinates legendLocation = new Coordinates(x,y);

//            shape = new Rectangle(legendLocation.getxMap(),legendLocation.getyMap(),100,40*(series.size()-1)+60);
//            g2d.setColor(Color.WHITE);
//            g2d.fill(shape);
//            g2d.setColor(Color.BLACK);
//            g2d.draw(shape);

         for(int i=0; i < series.size(); i++){
             if(series.get(i).isDrawMarker()){
                 switch(series.get(i).getMarkerShape()){
                     case "CIRCLE":
                         shape = new Ellipse2D.Double(legendLocation.getxMap()+10,legendLocation.getyMap()+40+40*i-series.get(i).getMarkerSize(), series.get(i).getMarkerSize(),series.get(i).getMarkerSize());
                         break;
                     case "SQUARE":
                         shape = new Rectangle(legendLocation.getxMap()+10,legendLocation.getyMap()+40+40*i-series.get(i).getMarkerSize(), series.get(i).getMarkerSize(),series.get(i).getMarkerSize());
                         break;
                     default:
                         break;
                 }
             }
             g2d.setColor(series.get(i).getMarkerColor()); // fill our marker
             g2d.fill(shape);     

             if(series.get(i).isDrawMarkerOutline()){     // outline our marker
                 g2d.setColor(Color.BLACK);
                 g2d.draw(shape);
             }

             g2d.drawString(series.get(i).getName(),legendLocation.getxMap()+40,legendLocation.getyMap()+40+40*i); // write the name
         }   
     }     
 }
