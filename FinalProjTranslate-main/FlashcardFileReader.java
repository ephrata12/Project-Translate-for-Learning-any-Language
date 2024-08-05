import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardFileReader implements Runnable {
    FlashcardFileReader() throws IOException{
    FlashcardDisplay.flashcards = new ArrayList<Flashcard>();
        FlashcardDisplay.learnedWords = new ArrayList<Boolean>();
       
    }

    @Override
    public void run() {
        try {
            Scanner s = new Scanner(new File("flashcards.txt"));
            Main.language = s.nextLine();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] things = line.split(",", 0);
                //System.out.println(Arrays.toString(things));
                String english = things[0];
                
                Flashcard flashCard = new Flashcard(english);
                FlashcardDisplay.flashcards.add(flashCard);
                FlashcardDisplay.learnedWords.add(false);
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.err.println(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (MainGraphics.partNumber == 1){
            Loading.frame.setVisible(Boolean.FALSE);
            Loading.frame.dispose();
            try {
                Part1 part1 = new Part1();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (MainGraphics.partNumber == 2){
            Loading.frame.setVisible(Boolean.FALSE);
            Loading.frame.dispose();
            try {
                Part2 part2 = new Part2();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (MainGraphics.partNumber == 3){
            Loading.frame.setVisible(Boolean.FALSE);
            Loading.frame.dispose();
            Part3Frame part3Frame = new Part3Frame();
        }
    }
    

}
