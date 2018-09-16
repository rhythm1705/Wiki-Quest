package backend;

import java.util.ArrayList;

public class TriviaQuestion {

    private String text;
    private ArrayList<String> answers;
    private String ans;

    public TriviaQuestion(String text, String ans, ArrayList<String> answers) {

        this.text = text;
        this.answers = answers;
        this.ans = ans;
    }
    public String getText(){
        return text;
    }
    public ArrayList<String> getWrongAnswers(){
        return answers;
    }
    public String getAns(){
        return ans;
    }

    @Override
    public String toString(){
        return text + ans + answers.get(0) + answers.get(1) + answers.get(2);
    }
}
