import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class TranslationDisplay {
private static JFrame frame = new JFrame("Translator");
//where user will type in English to be translated
private static JTextField textField = new JTextField(16);
//where translation will appear
private static JLabel translationLabel = new JLabel("");
private static JPanel buttonPanel;
private static JPanel fieldPanel;
private static JPanel menuJPanel;
private static Container myPane = frame.getContentPane();

    public TranslationDisplay() throws IOException {
        //pane for everything except the menu and translated text
        myPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setMyConstraints(c,0,0,GridBagConstraints.CENTER);
        fieldPanel = getFieldPanel();
        myPane.add(fieldPanel,c);
        setMyConstraints(c,0,1,GridBagConstraints.CENTER);
        buttonPanel = getButtonPanel();
        myPane.add(buttonPanel,c);
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.ipady = 0;
        cons.insets = new Insets(0, 0, 800, 0);
        cons.gridx = 0;
        cons.gridy = 0; 
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx =1;
        //returns cons
        menuJPanel = getMenuPanel();
        frame.add(menuJPanel, cons);
        JPanel translationPanel = getTranslationPanel();
        cons.insets = new Insets(500, 0, 0, 0);
        frame.add(translationPanel, cons);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 2000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //gets the directions
    private static JPanel getFieldPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setMinimumSize(new Dimension(600, 200));
        p.setMaximumSize(new Dimension(600, 200));
        p.setPreferredSize(new Dimension(600, 200));
        p.setBorder(BorderFactory.createTitledBorder("                          Please type in the English that you'd like to be translated below:                  "));
        GridBagConstraints c = new GridBagConstraints();
        setMyConstraints(c,1,0,GridBagConstraints.WEST);
        p.add(textField,c);
        return p;
    }

    private static JPanel getButton1(JPanel buttonJpanel){
        JButton button1 = new JButton("Translator");
        //menu panel
        buttonJpanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonJpanel.removeAll();
                buttonPanel.removeAll();
                while (myPane.getComponentCount()>0){
                    myPane.remove(0);
                }
                fieldPanel.removeAll();
                frame.setVisible(Boolean.FALSE);
                frame.dispose();
                try {
                    Part1 part1 = new Part1();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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
                //buttonJpanel.removeAll();
                menuJPanel.removeAll();
                buttonPanel.removeAll();
                while (myPane.getComponentCount()>0){
                    myPane.remove(0);
                }
                fieldPanel.removeAll();
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
        });
        return buttonJpanel;
    }

    private static JPanel getButton3(JPanel buttonJpanel){
        JButton button3 = new JButton("Asteroids");
        buttonJpanel.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuJPanel.removeAll();
                buttonPanel.removeAll();
                while (myPane.getComponentCount()>0){
                    myPane.remove(0);
                }
                fieldPanel.removeAll();
                frame.setVisible(Boolean.FALSE);
                frame.dispose();
                if (Main.translatingThread.isAlive()){
                    Loading loading = new Loading();
                }else{
                    Part3Frame part3 = new Part3Frame();
                }
            }
        });
        return buttonJpanel;
    }

   //if user pressed enter, translate their text and make it appear
    private static JPanel getButtonPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        JButton enter = new JButton("Enter");
            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String toTranslate = textField.getText();
                        Flashcard translation = new Flashcard(toTranslate);
                        String[] subStrings = translation.getTranslatedWord().split(" ");
                        Integer counter = 0;
                        String toAppear = "";
                        //add appropriate line breaks
                        for(String subString: subStrings) {
                            counter += 1;
                            toAppear += subString + " ";
                            if (counter > 20){
                                toAppear = toAppear + "<br/>";
                                counter = 0;
                            }
                        }
                        translationLabel.setText("<html>" + toAppear + "</html>");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            p.add(enter);
        return p;
    }

    private static JPanel getMenuPanel() {
        JPanel buttonJpanel = new JPanel(new GridBagLayout());
        buttonJpanel.setLayout(new GridLayout(1,2));
        buttonJpanel = getButton1(buttonJpanel);
        buttonJpanel = getButton2(buttonJpanel);
        buttonJpanel = getButton3(buttonJpanel);
        return buttonJpanel;
    }

   //panel of the translation
   private static JPanel getTranslationPanel() {
     JPanel p = new JPanel(new GridBagLayout());
      p.add(translationLabel);
      return p;
   }
   //method using gridbag so it is easier to set constraints, from an online source on Stackoverflow
   private static void setMyConstraints(GridBagConstraints c, int gridx, int gridy, int anchor) {
      c.gridx = gridx;
      c.gridy = gridy;
      c.anchor = anchor;
   }
}