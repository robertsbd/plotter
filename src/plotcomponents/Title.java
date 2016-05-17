package plotcomponents;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import data.Coordinates;

/**
 *
 * @author benjamin
 */
public class Title extends JPanel{ // draws a title, can be used for writing axis titles also. Will also write 
    
    private String title;
    private Font font;
    private String location; // which location (top, left, right, bottom)
    private int distanceFromFrame; // a negative int that will define how far from the plotting area the title should be located.
    private float proportionUpSide;

    public Title(){
        this.title = "";
        this.location = "TOP";
        this.distanceFromFrame = 0;
        this.font = null;            
    }

    public Title(String title, String location, int distanceFromFrame, float proprotionUpSide, Font font){
        this.title = title;
        this.location = location;
        this.distanceFromFrame = distanceFromFrame;
        this.font = font;
    }  

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        Coordinates titleCoord = new Coordinates();
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        switch (location){
            case "TOP":
                titleCoord = new Coordinates((titleCoord.getxMax()-titleCoord.getxMin())*proportionUpSide, titleCoord.getyMax());
                g2d.drawString(title, titleCoord.getxMap(), titleCoord.getyMap()+distanceFromFrame);
                break;
            case "BOTTOM":
                titleCoord = new Coordinates((titleCoord.getxMax()-titleCoord.getxMin())*proportionUpSide, titleCoord.getyMin());
                g2d.drawString(title, titleCoord.getxMap(), titleCoord.getyMap()-distanceFromFrame);
                break;
            case "LEFT":
                titleCoord = new Coordinates(titleCoord.getxMin(),(titleCoord.getyMax()-titleCoord.getyMin())*proportionUpSide);
                g2d.drawString(title, titleCoord.getxMap()+distanceFromFrame, titleCoord.getyMap());
                break;
            case "RIGHT":
                titleCoord = new Coordinates(titleCoord.getxMin(),(titleCoord.getyMax()-titleCoord.getyMax())*proportionUpSide);
                g2d.drawString(title, titleCoord.getxMap()-distanceFromFrame, titleCoord.getyMap());
                break;
        }
    }

    public void setTitle(String title, String location, int distanceFromFrame, float proportionUpSide, Font font){
        this.title = title;
        this.location = location;
        this.distanceFromFrame = distanceFromFrame;
        this.font = font;
        this.proportionUpSide = proportionUpSide;
    }

}
