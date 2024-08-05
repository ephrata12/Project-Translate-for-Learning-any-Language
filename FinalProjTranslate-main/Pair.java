class Pair{
    public double[] x;
    public double[] y;
    public String Original;
    public String Translated;

    public Pair(double[] initX, double[] initY){
        x = initX;
        y = initY;
    } //Constructor that allows pairs to store arrays containing doubles

    public Pair(String OriginalWord, String TranslatedWord){
        Original = OriginalWord;
        Translated = TranslatedWord;
    } //Constructor that allows pairs to store arrays containing Strings

    public Pair add(Pair toAdd){
        double[] resultX = new double[x.length];
        double[] resultY = new double[y.length];
        for (int i = 0; i < x.length; i++) {
            resultX[i] = x[i] + toAdd.x[i];
            resultY[i] = y[i] + toAdd.y[i];
        }
        return new Pair(resultX, resultY);
    } //A method that allows the addition of a value to an array of doubles. The array containing the result is returned

    public Pair times(double val){
        double[] resultX = new double[x.length];
        double[] resultY = new double[y.length];
        for (int i = 0; i < x.length; i++) {
            resultX[i] = x[i] * val;
            resultY[i] = y[i] * val;
        }
        return new Pair(resultX, resultY);
    } //A method that allows the multiplication of a value to an array of doubles. The array containing the result is returned

} //Pair class
