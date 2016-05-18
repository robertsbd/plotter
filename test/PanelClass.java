
import markers.InvertedTriangle;
import markers.Marker;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 *
 * @author benjamin
 */
public class PanelClass extends JPanel{
    
    public Marker myTriangle;
    
    public PanelClass(){
        myTriangle = new InvertedTriangle(100,100,100,100);   
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        myTriangle.draw(g);
        myTriangle.fill(g);
        System.out.println(myTriangle.getName());
    } 
    
}
