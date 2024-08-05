import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Translator {
    private String _fromLanguage;
    private String _toLanguage;
    public Translator () throws IOException{
       _fromLanguage = "en";
       //whatever user chooses in flashcard.txt
       _toLanguage = Main.language;
    }

    //translator from https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application
    public String translate(String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbzShhR-SAa_fM_Vk0n6OH5_VA2m_FM6SgFKofgDlaMmQQD1SfviyzWpCdEgkojcWdbujA/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + this._toLanguage +
                "&source=" + this._fromLanguage;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
