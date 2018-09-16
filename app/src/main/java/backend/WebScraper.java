package backend;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.example.belemy.helloworld_hackathon.MainActivity;
import com.example.belemy.helloworld_hackathon.first;

import org.jsoup.Jsoup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Random;

public class WebScraper {
    private ArrayList<String> categories;
    private static ArrayList<String> websites = new ArrayList<String>();
    private static ArrayList<String[]> outputData = new ArrayList<>();
    private Resources r;

    public WebScraper(ArrayList<String> l, Resources r){
        categories = l;
        this.r = r;

    }

    public ArrayList<TriviaQuestion> getQuestions(){
        /*startURLScrape(fillWebsites());
        //hang for end, check for outputdata not being empty
        return getQuestions(outputData);*/
        try {
            ArrayList<String> webs = fillWebsites();
            ArrayList<String[]> data = new getJsoupData().execute(webs).get();
            System.out.println(outputData);
            return getQuestions(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public ArrayList<String> fillWebsites(){

        //String[][] urls = new [["Geography", "https://en.wikipedia.org/wiki/Death_Valley"]];



        String s = " ";
        try {
            int id = r.getIdentifier("websites", "raw", first.pak);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                                    r.openRawResource(id)));

            while((s = reader.readLine())!=null){
                int space = s.indexOf(' ');
                String cat = s.substring(0,space);
                String url = s.substring(space+1,s.length());
                /*if(cat.equals("Sports")){
                    for(int i=0; i<categories.size(); i++){
                        if(categories.get(i).equals("Sports")){
                            websites.add(url);
                        }
                    }
                }else if(cat.equals("Geography")){
                    for(int i=0; i<categories.size(); i++){
                        if(categories.get(i).equals("Geography")){
                            websites.add(url);
                        }
                    }
                }else if(cat.equals("HistoricalFigures")){
                    for(int i=0; i<categories.size(); i++){
                        if(categories.get(i).equals("HistoricalFigures")){
                            websites.add(url);
                        }
                    }
                }else if(cat.equals("Combat")){
                    for(int i=0; i<categories.size(); i++){
                        if(categories.get(i).equals("Combat")){
                            websites.add(url);
                        }
                    }
                }else if(cat.equals("FineArts")){
                    for(int i=0; i<categories.size(); i++){
                        if(websites.get(i).equals("FineArts")){
                            websites.add(url);
                        }
                    }
                }*/

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
                    while(fakeans.contains(s.get(n)[0])) {
                        n = rand.nextInt(s.size());
                    }
                    fakeans.add(s.get(n)[0]);

                }
            }

            out.add(new TriviaQuestion(cleanString(s.get(i)[0], s.get(i)[1]), s.get(i)[0], fakeans));

        }
        return out;
    }

    /*public void startURLScrape(ArrayList<String> urls){
        new getJsoupData().execute(urls);
        while(outputData.size() == 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    public static void setJsoupData(ArrayList<String[]> data){
        outputData = data;
    }

    private static class getJsoupData extends AsyncTask<ArrayList<String>, Void, ArrayList<String[]>>{

        @Override
        protected ArrayList<String[]> doInBackground(ArrayList<String>... in){
            ArrayList<String> urls = in[0];
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

        @Override
        protected void onPostExecute(ArrayList<String[]> out){
            setJsoupData(out);
        }
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
