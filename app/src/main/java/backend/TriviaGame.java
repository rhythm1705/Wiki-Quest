package backend;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TriviaGame {

    private static TriviaGame instance = null;

    private static ArrayList<TriviaQuestion> questions;
    GameParameters params;
    private static ArrayList<Integer> randNums = new ArrayList<>();

    private TriviaGame(GameParameters params, Resources r){

        this.params = params;

        WebScraper w = new WebScraper(params.genres, r);

        questions = w.getQuestions();

        for(int i = 0; i < questions.size(); i++) {
            randNums.add(i);
        }
        Collections.shuffle(randNums);
    }

    /*public TriviaQuestion getQuestion(int q){
        return questions.get(randNums.get(q).intValue());
    }*/

    public TriviaQuestion getQuestion(){
        System.out.println(randNums.size());
        try {
            return questions.get(randNums.remove(0));
        }catch(IndexOutOfBoundsException e){
            System.out.println("ono");
        }
        return questions.get(0);
    }

    public static TriviaGame getInstance(ArrayList<String> strs, Resources r){
        if(instance == null){
            instance = new TriviaGame(new GameParameters(strs), r);
        }
        return instance;
    }

    public static void resetInstance(){
        instance = null;
    }

}
