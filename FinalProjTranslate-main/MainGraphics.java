import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainGraphics extends JPanel implements ActionListener {
    //directs program to given part after file is downloaded
    public static int partNumber;
    //keeps track of how many characters have been typed out in introduction
    private int charIndex = 0;
    //explains the program
    private String greeting;

    //Gradient code from: https://stackoverflow.com/questions/14364291/jpanel-gradient-background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color1 = Color.BLACK;
        Color color2 = Color.GREEN;
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    public MainGraphics() throws InterruptedException{
        greeting = "<html>Hello and welcome to Project Translate: The world's best langauge-learning and translation software for any language.<br/><br/><html>";
        greeting += "<html>Project Translate has three parts:<br/><br/>English to Any Language Translator: Interact with the terminal to translate from English to any language.<br/><br/><html>";
        greeting += "<html>Vocabulary Flashcards: Enter English words in flashcards.txt and quiz yourself on<br/><html>";
        greeting += "the automatically added translations.";
        greeting += "<html><br/><br/>Vocabulary Game: Type in the English translation of every vocabulary word before the Asteroid falls<br/>";
        greeting += "<html>to the ground.<br/><br/>Click on the menu above to choose your destiny.<html>";
        setLayout(new GridBagLayout());
        //menu bar
        JPanel buttonJpanel = new JPanel();
        buttonJpanel.setLayout(new GridLayout(1,2));
        JLabel label;
        label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        //button for translator
        // JButton button1 = new JButton("Translator");
        
        // buttonJpanel.add(button1);
        // button1.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Main.frame.setVisible(Boolean.FALSE);
        //         Main.frame.dispose();
        //         if (Main.translatingThread.isAlive()){
        //             Loading loading = new Loading();
        //             partNumber = 1;
        //         }else{
        //             try {
        //                 Part1 part1 = new Part1();
        //             } catch (IOException e1) {
        //                 // TODO Auto-generated catch block
        //                 e1.printStackTrace();
        //             }
        //         }
        //     }
        // });
        buttonJpanel = getButton1(buttonJpanel);
        buttonJpanel = getButton2(buttonJpanel);
        buttonJpanel = getButton3(buttonJpanel);
        // JButton button2 = new JButton("Quizlet");
        // //button2.setBounds(0, 0, 100, 100);
        // buttonJpanel.add(button2);
        // button2.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Main.frame.setVisible(Boolean.FALSE);
        //         Main.frame.dispose();
        //         if (Main.translatingThread.isAlive()){
        //             Loading loading = new Loading();
        //             partNumber = 2;
        //         }else{
        //             try {
        //                 Part2 part2 = new Part2();
        //             } catch (IOException e1) {
        //                 // TODO Auto-generated catch block
        //                 e1.printStackTrace();
        //             }
        //         }
        //     }
        // });
        // JButton button3 = new JButton("Asteroids");
        // //JButton button4 = new JButton("Button4");
        // buttonJpanel.add(button3);
        // button3.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Main.frame.setVisible(Boolean.FALSE);
        //         Main.frame.dispose();
        //         if (Main.translatingThread.isAlive()){
        //             Loading loading = new Loading();
        //             partNumber = 3;
        //         }else{
        //             Part3Frame part3 = new Part3Frame();
        //         }
        //     }
        // });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipady = 0;
        
        //pushes menu up
        constraints.insets = new Insets(0, 0, 400, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx =1;
        this.add(buttonJpanel, constraints);

        constraints.ipady = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(label, constraints);

        Main.frame.setLayout((LayoutManager) new BoxLayout(Main.frame.getContentPane(), BoxLayout.Y_AXIS));
        //Fun text typed out from: http://www.java2s.com/Tutorials/Java/Graphics_How_to/Text/Display_a_String_letter_by_letter_animation_in_a_JLabel.htm
        Timer timer = new Timer(45, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String labelText = label.getText();
                char toAdd = greeting.charAt(charIndex);
                labelText += toAdd;
                label.setText(labelText);
                charIndex++;
                if (charIndex >= greeting.length()) {
                    ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    private static JPanel getButton1(JPanel buttonJpanel){
        //button for translator
        JButton button1 = new JButton("Translator");
        
        buttonJpanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.setVisible(Boolean.FALSE);
                Main.frame.dispose();
                if (Main.translatingThread.isAlive()){
                    Loading loading = new Loading();
                    partNumber = 1;
                }else{
                    try {
                        Part1 part1 = new Part1();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
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
                if (Main.translatingThread.isAlive()){
                    Loading loading = new Loading();
                    partNumber = 2;
                }else{
                    try {
                        Part2 part2 = new Part2();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
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
                if (Main.translatingThread.isAlive()){
                    Loading loading = new Loading();
                    partNumber = 3;
                }else{
                    Part3Frame part3 = new Part3Frame();
                }
            }
        });
        return buttonJpanel;
    }

}

