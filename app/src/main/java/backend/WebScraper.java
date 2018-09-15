package backend;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class WebScraper {
    ArrayList<String> categories;
    ArrayList<String> websites = new ArrayList<String>();

    public WebScraper(ArrayList<String> l){
        categories = l;

    }
    public void fillWebsites(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Websites"));
            while(reader.readLine()!=null&&reader.readLine().charAt(0)){

            }


        }catch (IOException e) {

        }

    }
}
