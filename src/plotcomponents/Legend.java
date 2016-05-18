package plotcomponents;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
        Graphics2D g2d = (Graphics2D)g.create();   
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Coordinates legendLocation = new Coordinates(x,y);

        for(int i=0; i < series.size(); i++){ 
           series.get(i).getMarker().setX(legendLocation.getxMap()+10);
           series.get(i).getMarker().setY(legendLocation.getyMap()+40+40*i-series.get(i).getMarker().getSize());
           if(series.get(i).isDrawLine()){
               g2d.setColor(series.get(i).getLineColor());
               g2d.drawLine(legendLocation.getxMap(), legendLocation.getyMap()+40*i+40- (int)series.get(i).getMarker().getSize()/2, legendLocation.getxMap()+ (int) series.get(i).getMarker().getSize()*3, legendLocation.getyMap()+40*i+40- (int)series.get(i).getMarker().getSize()/2);
           }
           series.get(i).getMarker().fill(g); 
           if(series.get(i).getMarker().isOutline()) series.get(i).getMarker().draw(g);
           g2d.drawString(series.get(i).getName(),legendLocation.getxMap()+40,legendLocation.getyMap()+40+40*i); // write the name
       }
    }   
}
