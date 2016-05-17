package plotcomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import data.Coordinates;
import data.xyData;
import shapes.StraightLine;

/**
 *
 * @author benjamin
 */
public class DataSeries extends JPanel{ // this contains the information about the dataSeries

    private String name;
    private xyData data;
    private boolean drawMarker = false;
    private int markerSize;
    private Color markerColor;
    private String markerShape;
    private boolean drawMarkerOutline = false;
    private boolean drawLine = false;
    private Color lineColor;

    public DataSeries(){
    }

    public DataSeries(double data[][]){
        this.data = new xyData(data);
    }

    public DataSeries(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine, Color lineColor){   
        this.name = name;
        this.data = new xyData(data);
        this.drawMarker = drawMarker;
        this.markerSize = markerSize;
        this.markerColor = markerColor;
        this.markerShape = markerShape;
        this.drawMarkerOutline = drawMarkerOutline;          
        this.drawLine = drawLine;
        this.lineColor = lineColor;
    }

    public void setDataPoints(String name, double data[][], boolean drawMarker, int markerSize, Color markerColor, String markerShape, boolean drawMarkerOutline, boolean drawLine, Color lineColor){   
        this.name = name;
        this.data = new xyData(data);          
        this.drawMarker = drawMarker;
        this.markerSize = markerSize;
        this.markerColor = markerColor;
        this.markerShape = markerShape;
        this.drawMarkerOutline = drawMarkerOutline;           
        this.drawLine = drawLine;  
        this.lineColor = lineColor;
    }
    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(drawLine) addLines(g);
        if(drawMarker) addMarkers(g);
    }

    public void addMarkers(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // need to add some more shapes

        Shape shape;

        for(Coordinates point : data.getData()){
            switch (markerShape) {
                case "CIRCLE":
                    shape = new Ellipse2D.Double(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                     break;
                case "SQUARE":
                    shape = new Rectangle(point.getxMap()-markerSize/2, point.getyMap()-markerSize/2, markerSize, markerSize);
                    break;
                default:
                    shape = null;
                    break;
            }
            g2d.setColor(markerColor);
            g2d.fill(shape); 
            if( drawMarkerOutline){
                g2d.setColor(Color.BLACK);
                g2d.draw(shape);
            }
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

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
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
     * @return the drawMarker
     */
    public boolean isDrawMarker() {
        return this.drawMarker;
    }

    /**
     * @param drawMarker the drawMarker to set
     */
    public void setDrawMarker(boolean drawMarker) {
        this.drawMarker = drawMarker;
    }

    /**
     * @return the markerSize
     */
    public int getMarkerSize() {
        return markerSize;
    }

    /**
     * @param markerSize the markerSize to set
     */
    public void setMarkerSize(int markerSize) {
        this.markerSize = markerSize;
    }

    /**
     * @return the markerColor
     */
    public Color getMarkerColor() {
        return this.markerColor;
    }

    /**
     * @param markerColor the markerColor to set
     */
    public void setMarkerColor(Color markerColor) {
        this.markerColor = markerColor;
    }

    /**
     * @return the markerShape
     */
    public String getMarkerShape() {
        return this.markerShape;
    }

    /**
     * @param markerShape the markerShape to set
     */
    public void setMarkerShape(String markerShape) {
        this.markerShape = markerShape;
    }

    /**
     * @return the drawMarkerOutline
     */
    public boolean isDrawMarkerOutline() {
        return this.drawMarkerOutline;
    }

    /**
     * @param drawMarkerOutline the drawMarkerOutline to set
     */
    public void setDrawMarkerOutline(boolean drawMarkerOutline) {
        this.drawMarkerOutline = drawMarkerOutline;
    }

    /**
     * @return the drawLine
     */
    public boolean isDrawLine() {
        return this.drawLine;
    }

    /**
     * @param drawLine the drawLine to set
     */
    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
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
}