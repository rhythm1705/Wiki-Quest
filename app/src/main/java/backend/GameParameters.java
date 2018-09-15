package backend;
import java.util.ArrayList;

public class GameParameters {

    public ArrayList<String> genres;
    public int num_Lines;
    public int num_Questions;
    public int time_Question;

    public GameParameters(ArrayList<String> genres, int l, int q, int t) {
        this.genres = genres;
        num_Lines = l;
        num_Questions = q;
        time_Question = t;
    }
}
