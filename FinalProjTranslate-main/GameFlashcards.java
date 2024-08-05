import java.awt.*;

class GameFlashcards{
    String [] OriginalWord;
    String [] TranslatedWord;
    public GameFlashcards(){ //Constructor containing the original and translated flashcards sets of words
        OriginalWord = new String[FlashcardDisplay.flashcards.size()];
        TranslatedWord = new String[FlashcardDisplay.flashcards.size()];
        int counter = 0;
        for (Flashcard card : FlashcardDisplay.flashcards){
            OriginalWord[counter] = card.getEnglish();
            TranslatedWord[counter] = card.getTranslatedWord();
            counter += 1;
        }

    } //GameFlashcards constructor
} //GameFlashcards class

class Asteroid extends GameFlashcards{ //Subclass of GameFlashcards Class
    Pair position;
    Pair velocity;
    Pair pairWord;
    Pair positionWord;
    public Asteroid(){
        int WordNum = (int)(Math.random() * OriginalWord.length);
        double startX = (Math.random() * 824 + 230); //Randomly generated starting X-position
        int startY = 0;
        position = new Pair(new double[]{startX - 120, startX, startX + 60, startX + 40, startX - 50, startX - 140},new double[]{startY + 40, startY, startY + 60, startY + 180, startY + 200, startY + 140});
        velocity = new Pair(new double[]{0.0},new double[]{150.0});
        pairWord = new Pair(OriginalWord[WordNum],TranslatedWord[WordNum]); //Uses WordNum to randomly pick original word and corresponding translated word
        positionWord = new Pair(new double[]{startX - 60},new double[]{100.0});
    } //Asteroid constructor. It contains the needed information to generate an asteroid with a translated word on it. The original word chosen is one that needs to be typed by the user

    public void update(World w, double time){ //"World w" argument included as update method is used in the class Part3, where "World w" is a necessary argument
        for(int i = 0; i < position.x.length; i++){
            position.x[i] = position.x[i] + (velocity.x[0] * time);
            position.y[i] = position.y[i] + (velocity.y[0] * time);
        }
        positionWord = positionWord.add(velocity.times(time));
    } //Updates position of individual asteroid and position of translated word on the asteroid

    public void setPosition(Pair p){
        position = p;
    } //Sets position of an asteroid to a pair of coordinates

    public void setVelocity(Pair v){
        velocity = v;
    } //Sets velocity of an asteroid to a pair of velocity vectors

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        int[] xPoints = new int[position.x.length];
        int[] yPoints = new int[position.y.length];
        for (int i = 0; i < position.x.length; i++){ //Converts points on polygon from doubles to integers so the polygon can be drawn
            xPoints[i] = (int)position.x[i];
            yPoints[i] = (int)position.y[i];
        }
        g.fillPolygon(xPoints,yPoints,6);
        g.setColor(Color.BLACK);
        g.drawString(pairWord.Translated,(int)(positionWord.x[0]),(int)(positionWord.y[0]));
    } //Draws an asteroid with a translated word on it

} //Asteroid class
