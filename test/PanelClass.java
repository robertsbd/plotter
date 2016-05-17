
import java.awt.Graphics;
import javax.swing.JPanel;
import shapes.*;


/**
 *
 * @author benjamin
 */
public class PanelClass extends JPanel{
    
    public Shape myTriangle;
    
    public PanelClass(){
        myTriangle = new Triangle(100,100,100,100);   
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        myTriangle.draw(g);
        myTriangle.fill(g);
        System.out.println(myTriangle.getName());
    } 
    
}
