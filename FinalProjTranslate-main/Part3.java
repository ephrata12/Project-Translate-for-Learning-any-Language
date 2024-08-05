import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Part3 extends JPanel implements KeyListener{
    public static JFrame frame = new JFrame();
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 2000;
    public static final int FPS = 60;
    World world;
    String User = "";
    int Points = 0; //Counts number of asteroid the user removed
    class Runner implements Runnable {
        public void run(){
            while (true) {
                world.FrameCount++; //Updates FrameCount variable for LoadingScreen method in world
                world.updateAsteroids(1.0 / (double) FPS); //Updates position of asteroids on screen
                repaint(); //Repaints asteroids with updated positions
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    System.out.println("Error in run() method!");
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        char[]PossibleKeys = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','\u0008','-'};
        for (char possibleKey : PossibleKeys) {
            if (possibleKey == c) { //If the typed is in the PossibleKeys array, it will be added to the User String
                User += c;
                Pair newPosition;
                Pair newVelocity;
                for (int j = (world.numAsteroids - 1); j >= 0; j--) {
                    if (c == '\u0008') {
                        User = "";
                    } //If the Backspace key is pressed, the User String is reset
                    if (world.asteroids[j] != null) { //Avoids a NullPointerException
                        if (User.equals(world.asteroids[j].pairWord.Original)) {
                            double[] newPositionX = {512.0, 512.0, 512.0, 512.0, 512.0, 512.0};
                            double[] newPositionY = {-150.0, -150.0, -150.0, -150.0, -150.0, -150.0};
                            double[] newEndVelocity = {0.0};
                            newPosition = new Pair(newPositionX, newPositionY);
                            newVelocity = new Pair(newEndVelocity, newEndVelocity);
                            world.asteroids[j].setPosition(newPosition);
                            world.asteroids[j].setVelocity(newVelocity);
                            world.asteroids[j].pairWord.Original = null;
                            User = "";
                            Points++;
                        } //If the User string is the same as one of the original words of an asteroid, the asteroid will be removed from the screen and the User String will be reset. The user also gains a point
                    }
                } //Searching the all the asteroids in the array to determine if the described condition above is met
            }
        } //For-Loop for PossibleKeys array
    } //KeyReleased method

    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
    } //Needed for KeyListener to operate. No used for anything else

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    } //Needed for KeyListener to operate. No used for anything else

    public void drawString(Graphics g){
        if(world.LifeCounter > -1){
            g.setColor(Color.CYAN);
            Font font = new Font("Serif",Font.PLAIN,15);
            g.setFont(font);
            g.drawString("Your Guess: " + User,10,800);
            g.drawString("Your Points: " + Points,10,770);
            repaint();
        } //Drawing only occurs when game is running and the user hasn't lost all the lives
        if(world.LifeCounter == -1){
            g.setColor(Color.BLACK);
            Font font = new Font("Arial",Font.BOLD,75);
            g.setFont(font);
            g.drawString("Your Score: " + Points,530,560);
        }
    } //Draws the user's inputs and points onto the screen

    public void addNotify() {
        super.addNotify();
        requestFocus();
    } //Makes sure the game runs smoothly on the user's screen

    public Part3(BorderLayout b){
        JPanel buttonJpanel = new JPanel();
        buttonJpanel = getButton1(buttonJpanel);
        buttonJpanel = getButton2(buttonJpanel);
        buttonJpanel = getButton3(buttonJpanel);
        
        world = new World(WIDTH, HEIGHT, 5000);
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
        frame = new JFrame("Project");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 0;
        c.insets = new Insets(0, 0, 820, 0);
        c.gridx = 0;
        c.gridy = 0; 
        c.weightx =1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonJpanel, c);

        buttonJpanel.setLayout(new GridLayout(1,2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    } //The Part3 constructor contains the code that closes the main menu screen and opens another screen of the part the user clicked. Finding which part/button user clicked is determined using the ActionListener interface

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT); //Background of the screen

        world.loadingScreen(g);

        world.drawAsteroids(g);

        world.LifeLost(g);

        drawString(g);

    } //Calls the various draw methods and executes them so that it is actually drawn on the screen

    private static JPanel getButton1(JPanel buttonJpanel){
        buttonJpanel.setLayout(new GridLayout(1,2));
            JButton button1 = new JButton("Translator");
                    buttonJpanel.add(button1);
                    button1.addActionListener((ActionListener) new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Main.frame.setVisible(Boolean.FALSE);
                                Main.frame.dispose();
                                frame.setVisible(Boolean.FALSE);
                            frame.dispose();
                                Part1 part1 = new Part1();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }); //The main screen closes and the Part1 screen opens if the "Translator" button is clicked
        return buttonJpanel;
    }

    private static JPanel getButton2(JPanel buttonJpanel){
        JButton button2 = new JButton("Quizlet");
                    buttonJpanel.add(button2);
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Main.frame.setVisible(Boolean.FALSE);
                            Main.frame.dispose();
                            frame.setVisible(Boolean.FALSE);
                            frame.dispose();
                            if (Main.translatingThread.isAlive()){
                                Loading loading = new Loading();
                            }else{
                                try {
                                    Part2 part2 = new Part2();
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }); //The main screen closes and the Part2 screen opens if the "Quizlet" button is clicked
        return buttonJpanel;
    }

    private static JPanel getButton3(JPanel buttonJpanel){
        JButton button3 = new JButton("Asteroids");
                    buttonJpanel.add(button3);
                    button3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Main.frame.setVisible(Boolean.FALSE);
                            Main.frame.dispose();
                            frame.setVisible(Boolean.FALSE);
                            frame.dispose();
                            if (Main.translatingThread.isAlive()){
                                Loading loading = new Loading();
                            }else{
                                Part3Frame part3 = new Part3Frame();
                            }
                        }
                    }); //The main screen closes and the Part3 screen opens if the "Asteroids" button is clicked
        return buttonJpanel;
    }
} //Part3 class
