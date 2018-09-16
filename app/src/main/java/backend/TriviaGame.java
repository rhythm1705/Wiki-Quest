package backend;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TriviaGame {

    static TriviaGame instance;

    ArrayList<TriviaQuestion> questions;
    GameParameters params;
    ArrayList<Integer> randNums = new ArrayList<>();

    private TriviaGame(GameParameters params, Resources r){

        this.params = params;

        WebScraper w = new WebScraper(params.genres, r);

        questions = w.getQuestions();

        for(int i = 0; i < questions.size(); i++) {
            randNums.add(new Integer(i));
        }
        Collections.shuffle(randNums);
    }

    public TriviaQuestion getQuestion(int q){
        return questions.get(randNums.get(q).intValue());
    }

    public static TriviaGame getInstance(ArrayList<String> strs, Resources r){
        if(instance == null){
            instance = new TriviaGame(new GameParameters(strs), r);
        }
        return instance;
    }

}
