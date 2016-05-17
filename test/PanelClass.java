
import java.awt.Graphics;
import javax.swing.JPanel;
import shapes.*;


/**
 *
 * @author benjamin
 */
public class PanelClass extends JPanel{
    
    public Shape mySquare;
    
    public PanelClass(){
        mySquare = new Square(100,100,100);   
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mySquare.draw(g);
        mySquare.fill(g);
        System.out.println(mySquare.getName());
    } 
    
}
