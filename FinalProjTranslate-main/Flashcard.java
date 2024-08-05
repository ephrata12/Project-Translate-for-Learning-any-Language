import java.io.IOException;
import java.util.Date;

public class Flashcard {
    Translator _shortTranslator;
    private String english_word;
    private String translatedWord;
    private boolean learnedWord;

    // Constructor method to initialize the flashcard with English and Russian words.
    public Flashcard(String english_word) throws IOException {
        _shortTranslator = new Translator();
        this.english_word=english_word;
        this.translatedWord = _shortTranslator.translate(english_word);
        System.out.println(translatedWord);

    }

    // Getter method to return the English word on the flashcard.
    public String getEnglish(){
        return english_word;
    }

    // Getter method to return the translated word on the flashcard.
    public String getTranslatedWord(){
        return translatedWord;
    }

    // Getter method to return whether the word on the flashcard has been learned or not.
    public boolean getLearnedWord(){
        return learnedWord;
    }

    public boolean setNeedsRevision(boolean needsRevision) {
        // If the word needs revision, set the learnedWord flag to false.
        if (needsRevision) {
            learnedWord = false;
        } else {
            // else, set the learnedWord flag to true.
            learnedWord = true;
        }
        return needsRevision;
    }
}

