package backend;
import java.util.ArrayList;

public class TriviaGame {

    ArrayList<TriviaQuestion> questions;
    GameParameters params;

    public TriviaGame(GameParameters params){

        this.params = params;

        WebScraper w = new WebScraper(params.genres);

        questions = w.getQuestions();
        System.out.println(questions.get(0).toString());
    }

    public static void main(String[] args){
                TriviaGame t = new TriviaGame(new GameParameters());
    }


}
