import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

class World{
    int height;
    int width;

    int currentAsteroidIndex = 0;

    int numAsteroids; 
    Asteroid[] asteroids;

    int FrameCount = 0;

    int LifeCounter;

    public World(int initWidth, int initHeight, int initNumAsteroids){
        width = initWidth;
        height = initHeight;

        numAsteroids = initNumAsteroids;
        asteroids = new Asteroid[numAsteroids]; //Creation of the asteroids array

        int delay = 4000; //Initial delay before the first asteroid is generated
        int delay1 = 2800; //Time delay between the creation of two asteroids

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                asteroids[currentAsteroidIndex] = new Asteroid(); //A new asteroid is created
                currentAsteroidIndex++; //Length of array update so another asteroid can be drawn in the future
            } //Overrides the run() method in the Part3 class to create every asteroid at a time delay using Timer and TimerTask
        }, delay, delay1);
    } //World constructor creates the array of asteroids and creates them at a specific time delay

    public void loadingScreen(Graphics g){
        if(FrameCount < 150){
            Font font = new Font("Arial",Font.PLAIN,85);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString("Loading",590,420);
        } //Draws the word "Loading" on the screen while FrameCount is below 150
        if((FrameCount < 15) || (FrameCount > 60 && FrameCount < 75) || (FrameCount > 120 && FrameCount < 135)){
            g.setColor(Color.YELLOW);
            g.fillOval(900,410,10,10);
        } //When the frame count conditions are met, one yellow circle will be drawn to the right of "Loading" on the screen
        if((FrameCount > 15 && FrameCount < 30) || (FrameCount > 75 && FrameCount < 90) || (FrameCount > 135 && FrameCount < 150)){
            g.setColor(Color.YELLOW);
            g.fillOval(900,410,10,10);
            g.fillOval(920,410,10,10);
        } //When the frame count conditions are met, two yellow circles will be drawn to the right of "Loading" on the screen
        if((FrameCount > 30 && FrameCount < 45) || (FrameCount > 90 && FrameCount < 105)){
            g.setColor(Color.YELLOW);
            g.fillOval(900,410,10,10);
            g.fillOval(920,410,10,10);
            g.fillOval(940,410,10,10);
        } //When the frame count conditions are met, three yellow circles will be drawn to the right of "Loading" on the screen
        if(FrameCount > 110 && FrameCount < 150){
            Font font = new Font("Arial",Font.PLAIN,15);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString("(Get Ready!)",730,460);
        } //Draws "(Get Ready)" to let the users know the first asteroid will appear soon at the end of the time the loading screen is shown
    } //Draws a loading screen before the first sphere is created. FrameCount uses the time from the run() method in the Part3 class

    public void drawAsteroids(Graphics g){
        for (int i = 0; i < currentAsteroidIndex; i++){
            if(asteroids[i] != null){ //Avoids NullPointerException when the asteroids are drawn in the PaintComponent method
                asteroids[i].draw(g);
            }
        }
    } //Draw the asteroids and translated word for all asteroids in the array

    public void updateAsteroids(double time){
        for (int i = 0; i < currentAsteroidIndex; i ++){
            if(asteroids[i] != null){ //Avoids NullPointerException when the asteroids are updated in Part3's run() method
                asteroids[i].update(this, time);
            }
        }
    } //Updates the position of the asteroids and that of the translated word for all asteroids in the array

    public void LifeLost(Graphics g) {
        LifeCounter = 3;
        for (int i = 0; i < currentAsteroidIndex; i++) {
            if (asteroids[i] != null) { // Avoids NullPointerException
                if (asteroids[i].position.y[1] > 900) {
                    LifeCounter--;
                    asteroids[i].pairWord.Original = null; //Equals null so the User can't type the word later and regain a life
                } //If the asteroid falls below the screen, the user will lose a life
            }
        }
        g.setColor(Color.RED);
        g.fillRect(10, 820, 10, 10);
        g.fillRect(30, 820, 10, 10);
        g.fillRect(50, 820, 10, 10);
        //Initial amount of lives the user will have when the game is created. Three red boxes are drawn signalling the user has four lives remaining
        if (LifeCounter == 2) {
            g.setColor(Color.BLACK);
            g.fillRect(50, 820, 10, 10);
        } //When the LifeCounter condition is met, two red boxes will be drawn, signalling the user has three lives remaining
        if (LifeCounter == 1) {
            g.setColor(Color.BLACK);
            g.fillRect(50, 820, 10, 10);
            g.setColor(Color.BLACK);
            g.fillRect(30, 820, 10, 10);
        } //When the LifeCounter condition is met, one red boxes will be drawn, signalling the user has two lives remaining
        if (LifeCounter == 0) {
            g.setColor(Color.BLACK);
            g.fillRect(50, 820, 10, 10);
            g.setColor(Color.BLACK);
            g.fillRect(30, 820, 10, 10);
            g.setColor(Color.BLACK);
            g.fillRect(10, 820, 10, 10);
        } //When the LifeCounter condition is met, no red boxes will be drawn, signalling the user has one life remaining
        if (LifeCounter <= -1) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 120);
            g.setFont(font);
            g.drawString("GAME OVER!", 370, 450);
            Pair endVelocity;
            double[] newEndVelocity = {0.0};
            for (int i = 0; i < numAsteroids; i++) {
                if (asteroids[i] != null) {
                    endVelocity = new Pair(newEndVelocity, newEndVelocity);
                    asteroids[i].setVelocity(endVelocity);
                    asteroids[i].pairWord.Original = null;
                }
            }
        } //When the LifeCounter condition is met, the game is over. The screen will turn red and say "GAME OVER." The original words are all set to null so the user can't somehow regain a life
    } //LifeLost method tracks if an asteroid left the screen and adjusts the number of lives accordingly. It also detects if the game is over.

} //The World class
