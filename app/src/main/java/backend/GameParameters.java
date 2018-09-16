package backend;
import java.util.ArrayList;

public class GameParameters {

    public static ArrayList<String> genres = new ArrayList<>();
    public static int time_Question;

    public GameParameters(ArrayList<String> g) {
        for(String i:g){
            genres.add(i);
        }
    }
}
