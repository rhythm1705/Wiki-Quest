package backend;
import java.util.ArrayList;

public class GameParameters {

    public static ArrayList<String> genres = new ArrayList<>();
    public static int num_Questions;
    public static int time_Question;

    public GameParameters() {
        genres.add("HistoricalFigures");
        genres.add("FineArts");
        genres.add("Combat");

    }
}
