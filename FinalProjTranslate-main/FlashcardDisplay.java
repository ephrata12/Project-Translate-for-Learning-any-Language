import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
class FlashcardDisplay extends JFrame {
    public static List<Flashcard> flashcards;// A list of flashcards to display
    private int cardNum = 0;// The current flashcard being displayed
    private boolean isFlipped = false; // Whether the current flashcard is flipped or not
    private JLabel label; // A label to display the current flashcard text
    public static List<Boolean> learnedWords; // A list of booleans indicating whether each word has been learned
    public static JPanel buttonJpanel;
    private final String LEARNED_WORDS_FILE = "learnedwords.txt";
    private static JPanel buttonPanel;

    public FlashcardDisplay() throws IOException {
        // Create a new window to display the flashcards
        JFrame frame = new JFrame("Quizlet");
        // Create a panel to hold the flashcard and buttons
        JPanel panel = new JPanel(new BorderLayout());
        buttonJpanel = new JPanel();
        buttonJpanel.setLayout(new GridLayout(1,2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create a label to display the current flashcard text
        label = new JLabel(flashcards.get(cardNum).getEnglish(), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 80));
        panel.add(label, BorderLayout.CENTER);
        // Add a button to shuffle the flashcards
        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleCards();
            }
        });

        // read code from stack overflow and watched video on how to add, rescale and make the buttons
        // but didn't copy the code.

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the card number and learned words list
                cardNum = 0;
                learnedWords.clear();
                for (int i = 0; i < learnedWords.size(); i++) {
                    learnedWords.set(i, false);
                }
                // Read the flashcards data from the file
                try (Scanner scanner = new Scanner(new File(LEARNED_WORDS_FILE))) {
                    while (scanner.hasNextLine()) {
                        String learnedWord = scanner.nextLine().trim();
                        for (int i = 0; i < flashcards.size(); i++) {
                            if (flashcards.get(i).getEnglish().equals(learnedWord)) {
                                learnedWords.add(true);
                                break;
                            }
                            if (i == flashcards.size() - 1) {
                                learnedWords.add(false);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Update the UI with the first card
                label.setText( flashcards.get(cardNum).getEnglish());
                isFlipped = false;
            }
        });
        // Add a right button
        String imagePathr = "right_button.png";
        ImageIcon rightIcon = new ImageIcon(imagePathr);
        Image imager = rightIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(imager);
        JButton rightButton = new JButton(resizedIcon);
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the current card to the list of learned words
                try (FileWriter writer = new FileWriter(LEARNED_WORDS_FILE, true)) {
                    writer.write(flashcards.get(cardNum).getEnglish() + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Move to the next card
                cardNum++;
                if (cardNum < flashcards.size()) {
                    label.setText( flashcards.get(cardNum).getEnglish());
                    isFlipped = false;
                } else {
                    panel.remove(buttonPanel);
                    panel.remove(label);
                    panel.add(createCongratulationsPanel(), BorderLayout.CENTER);
                    panel.revalidate();
                    panel.repaint();
                }
            }
        });

        //Add a button X
        String imagePathx = "X_button.png";
        ImageIcon xIcon = new ImageIcon(imagePathx);
        Image imagex = xIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconx = new ImageIcon(imagex);
        JButton xButton = new JButton(resizedIconx);
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mark the current card for revision
                flashcards.get(cardNum).setNeedsRevision(true);
                // Move the current card to the end of the list
                Flashcard currentCard = flashcards.remove(cardNum);
                flashcards.add(currentCard);
                // Move to the next card
                cardNum++;
                while (cardNum < flashcards.size() && flashcards.get(cardNum).setNeedsRevision(true)) {
                    cardNum++;
                }
                if (cardNum >= flashcards.size()) {
                    cardNum = 0;
                }
                // Show the next card
                label.setText(flashcards.get(cardNum).getEnglish());
                isFlipped = false;
            }
        });

        JButton button1 = new JButton("Translator");
        buttonJpanel.add(button1);
        button1.addActionListener(new ActionListener() {
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
        });
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
        });
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
        });
        // Add buttons for flipping the word
        String imagePathf = "flip_button.png";
        ImageIcon flipIcon = new ImageIcon(imagePathf);
        Image imagef = flipIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconf = new ImageIcon(imagef);
        JButton flipButton = new JButton(resizedIconf);
        flipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isFlipped = !isFlipped;
                if (isFlipped) {
                    label.setText(flashcards.get(cardNum).getTranslatedWord());
                } else {
                    label.setText(flashcards.get(cardNum).getEnglish());
                }
            }
        });
        // Add buttons for moving forward and backward
        String imagePathb = "backward_button.png";
        ImageIcon backIcon = new ImageIcon(imagePathb);
        Image imageb = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconb = new ImageIcon(imageb);
        JButton backwardButton = new JButton(resizedIconb);
        backwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isFlipped = false;
                cardNum--;
                if (cardNum < 0) cardNum = flashcards.size() - 1;
                label.setText(flashcards.get(cardNum).getEnglish());
            }
        });
        String imagePathn = "forward_arrow.png";
        ImageIcon nextIcon = new ImageIcon(imagePathn);
        Image imagen = nextIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIconn = new ImageIcon(imagen);
        JButton forwardButton = new JButton(resizedIconn);
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isFlipped = false;
                cardNum = (cardNum + 1) % flashcards.size();
                label.setText(flashcards.get(cardNum).getEnglish());
            }
        });
        // Add buttons to a new JPanel
        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(shuffleButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(xButton);
        buttonPanel.add(backwardButton);
        buttonPanel.add(flipButton);
        buttonPanel.add(forwardButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(buttonJpanel, BorderLayout.NORTH);
        frame.add(panel);
        frame.setSize(2000, 2000);
        frame.setVisible(true);
        this.setFocusable(true);
    }
    private JPanel createCongratulationsPanel() {
        // Create a new JPanel with a vertical BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Create a JLabel containing the  message
        JLabel congratulationsLabel = new JLabel(" You mastered the cards!!!!");
        congratulationsLabel.setFont(new Font("Arial", Font.BOLD, 40));
        congratulationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(congratulationsLabel);
        panel.add(Box.createVerticalGlue());
        return panel;
    }
    //shuffles the flashcards list,
    private void shuffleCards() {
        Collections.shuffle(flashcards);
        cardNum = 0;
        isFlipped = false;
        label.setText(flashcards.get(cardNum).getEnglish());
    }
}


