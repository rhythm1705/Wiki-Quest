package backend;
import java.util.ArrayList;
import java.util.Random;

public class TriviaGame {

    ArrayList<TriviaQuestion> questions;
    GameParameters params;

    public TriviaGame(GameParameters params){

        Random r = new Random();

        this.params = params;

        WebScraper w = new WebScraper(params.genres);


        questions = w.getQuestions();
        int num = (int)((r.nextDouble())*(w.getNumWebsites()));


        System.out.println(questions.get(num).toString());
    }

    public static void main(String[] args){
                TriviaGame t = new TriviaGame(new GameParameters());
    }


}
