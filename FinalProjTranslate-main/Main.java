import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.IOException;

//class to run whole program
public class Main extends JPanel {
    public static final int WIDTH=2000;
    public static final int HEIGHT=2000;
    public static JFrame frame;
    public static Thread translatingThread;
    public static String language;

    public static void main(String[] args) throws IOException, InterruptedException {
        frame = new JFrame( "Project Translate");
        //close frame when user clicks out
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //graphics for start screen
        MainGraphics panel = new MainGraphics();
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //create a thread to run that will download and translate words in flashcards.txt
        translatingThread = new Thread(new FlashcardFileReader());
        translatingThread.start();
    }
}