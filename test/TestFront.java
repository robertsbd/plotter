
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author benjamin
 */
public class TestFront {
 
        public static void main(String[] args) {
            
            JFrame myFrame = new JFrame();
            JPanel myPanel = new JPanel();
            myPanel = new PanelClass();
            myFrame.setSize(800,800);
            myFrame.add(myPanel);
            myFrame.setVisible(true);
        }
}
