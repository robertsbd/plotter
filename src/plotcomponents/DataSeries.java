package plotcomponents;

import markers.Marker;
import graphics.StraightLine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import data.Coordinates;
import data.xyData;

/**
 *
 * @author benjamin
 */
public class DataSeries extends JPanel{ // this contains the information about the dataSeries

    private String name;
    private xyData data;
    private Marker marker;
    private boolean drawLine = false; // we shouldn't need this, should just be able to tell by line Color.
    private Color lineColor;

    public DataSeries(){
    }

    public DataSeries(double data[][]){
        this.data = new xyData(data);
    }

    public DataSeries(String name, double data[][], Marker marker, boolean drawLine, Color lineColor){   
        this.name = name;
        this.data = new xyData(data);
        this.marker = marker;
        this.drawLine = drawLine;
        this.lineColor = lineColor;
    }

    public void setDataPoints(String name, double data[][], Marker marker, boolean drawLine, Color lineColor){   
        this.name = name;
        this.data = new xyData(data);          
        this.marker = marker;
        this.drawLine = drawLine;   
        this.lineColor = lineColor;
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(drawLine) addLines(g);
        if(marker.getSize()!=0) addMarkers(g);
    }

    public void addMarkers(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(Coordinates point : data.getData()){ 
            marker.setX(point.getxMap()-marker.getSize()/2);
            marker.setY(point.getyMap()-marker.getSize()/2);
            marker.fill(g); 
            if( marker.isOutline()) marker.draw(g);
        }
    }

    public void addLines(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(lineColor);

        for(int i = 1; i < data.getData().length; i++){
            StraightLine plotLine = new StraightLine(data.getDataPoint(i-1), data.getDataPoint(i));
            g2d.drawLine(plotLine.getStart().getxMap(), plotLine.getStart().getyMap(), plotLine.getEnd().getxMap(), plotLine.getEnd().getyMap());                
        }

    }

    // should be able to get rid of a lot of these
    
    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * @return the data
     */
    public xyData getData() {
        return this.data;
    }

    /**
     * @param data the data to set
     */
    public void setData(xyData data) {
        this.data = data;
    }


    /**
     * @param drawLine the drawLine to set
     */
    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
    }
    
    public boolean isDrawLine() {
        return this.drawLine;
    }

    /**
     * @return the lineColor
     */
    public Color getLineColor() {
        return this.lineColor;
    }

    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @return the marker
     */
    public Marker getMarker() {
        return marker;
    }
}