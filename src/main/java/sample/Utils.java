package sample;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    //Zadaniem tej metody, będzie odczytanie zawartości strony
    public static String makeRequest(String url){
        try {
            HttpURLConnection http = (HttpURLConnection) new URL(url).openConnection();
            http.setRequestMethod("GET");

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
