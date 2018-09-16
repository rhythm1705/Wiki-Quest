package backend;

import org.jsoup.Jsoup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Random;

public class WebScraper {
    private ArrayList<String> categories;
    private static ArrayList<String> websites = new ArrayList<String>();
    private int numberOfWebsites;

    public WebScraper(ArrayList<String> l){
        categories = l;

    }

    public ArrayList<TriviaQuestion> getQuestions(){
        return getQuestions(scrapeURL(fillWebsites()));
    }
    public ArrayList<String> fillWebsites(){
        String s = " ";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\justi\\AndroidStudioProjects\\program\\app\\src\\main\\java\\backend\\Websites"));

            while((s = reader.readLine())!=null){
                int space = s.indexOf(' ');
                String cat = s.substring(0,space);
                String url = s.substring(space+1,s.length());

                if(categories.contains(cat)){
                    websites.add(url);
                }

            }


        }catch (IOException e) {
            System.out.println("I'm not working");

        }
        return websites;
    }

    public ArrayList<TriviaQuestion> getQuestions(ArrayList<String[]> s){
        ArrayList<TriviaQuestion> out = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < s.size(); i++){
            ArrayList<String> fakeans = new ArrayList<>();
            while(fakeans.size() < 3){
                int n = rand.nextInt(s.size());
                if(n != i){
                    fakeans.add(s.get(n)[0]);
                }
            }

            out.add(new TriviaQuestion(cleanString(s.get(i)[0], s.get(i)[1]), s.get(i)[0], fakeans));

        }
        return out;
    }

    public ArrayList<String[]> scrapeURL(ArrayList<String> urls){
        ArrayList<String[]> out = new ArrayList<>();

        for(int i = 0; i < urls.size(); i++){
            try {
                Document doc = Jsoup.connect(urls.get(i)).get();                                               //connecting to website
                Elements paragraphs = doc.select("p");

                String html = paragraphs.get((int)(Math.random() * paragraphs.size())).toString();      //get random paragraph
                Document d = Jsoup.parse(html);
                Element p = d.select("p").first();
                String text = p.text();
                while (text.equals(" ") || text.equals("")) {                                           //if paragraph is empty, select a different one
                    html = paragraphs.get((int)(Math.random() * paragraphs.size())).toString();
                    d = Jsoup.parse(html);
                    p = d.select("p").first();
                    text = p.text();
                }
                text = text.substring(0, text.lastIndexOf(".") + 1);                                //omit anything after the last period


                Element title = doc.getElementById("firstHeading");                                     //accesses the title of the website
                String[] s = {title.text(), text};
                out.add(s);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return out;
    }
    public String cleanString(String ans, String body){
        String temp = body;
        String[] s = ans.split(" ");

        for (int i = 0; i < s.length; i++) {
            while (temp.contains(s[i])) {
                int index = temp.indexOf(s[i]);
                temp = temp.substring(0, index) + "______" + temp.substring(index + s[i].length(), temp.length());
            }
        }
        temp = removeBetween(temp, "(", ")");
        temp = removeBetween(temp, "[", "]");
        return temp;

    }

    public String removeBetween(String text, String start, String end){
        String temp = text;
        while (temp.contains(start) && temp.contains(end)) {
            temp = temp.substring(0, temp.indexOf(start)) + "______" +
                    temp.substring(temp.indexOf(end) + 1);
        }
        return temp;
    }
    public int getNumWebsites(){
        return websites.size();
    }
    /*public static void main (String[] args){
        ArrayList<String> list = new ArrayList<String>();
        list.add("FineArts");
        list.add("Geography");
        list.add("Sports");
        WebScraper scraper = new WebScraper(list, 3);
        scraper.fillWebsites();
        for(int i=0; i<websites.size(); i++){
            System.out.println(websites.get(i));
        }
    }*/
}
