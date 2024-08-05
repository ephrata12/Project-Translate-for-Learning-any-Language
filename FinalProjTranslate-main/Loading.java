import java.awt.*;
import javax.swing.*;

//Provides Loading Screen for when Flashcards are still being processed at beginning.
class Loading extends JPanel {
    public static final int WIDTH=1024;
    public static final int HEIGHT=768;
  
    static JFrame frame;
    static JLabel label;
  
    public Loading()
    {
        frame = new JFrame("Loading");
        // Creating a label to display text
        label = new JLabel("Loading... Ready in less than 30 seconds.", SwingConstants.CENTER);
        //set font type and size
        label.setFont(new Font("SansSerif", Font.PLAIN, 30));
        //set size of label
        Dimension size = label.getPreferredSize();
        label.setBounds(450, 350, size.width, size.height);
        //set label color to black
        label.setForeground(Color.black);
        // Creating a panel to add label
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.getContentPane();
        panel.add(label);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adding panel to frame
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(2000, 2000);
        //make frame visible
        frame.setVisible(true);
        repaint();
    }
}