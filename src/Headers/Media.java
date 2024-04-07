package Headers;

import java.util.HashMap;
import java.util.Map;

public abstract class Media {
    private String name;
    protected int id;
    private static int idMax = 0;
    private double rating = 0;
    private int numberOfReviews = 0;
    protected static Map<Integer, Media> productions = new HashMap<>();

    public Media(String name) {
        this.name = name;
        idMax++;
        id = idMax;
    }

    public int getId() {
        return id;
    }


    public static Media getById(int val) {
        return productions.getOrDefault(val, null);
    }

    public abstract String shortDescription();
    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + Integer.toString(this.id) + "\n";
        s += "Rating: " + Double.toString(rating) + "\n";
        return s;
    }

    public void eraseReview(int val) {
        double sum = rating * numberOfReviews;
        sum -= val;
        numberOfReviews--;
        if(numberOfReviews == 0) rating = 0;
        else rating = sum / numberOfReviews;
    }
    public void addReview(int val) {
        double sum = rating * numberOfReviews;
        sum += val;
        numberOfReviews++;
        rating = sum / numberOfReviews;
    }

    public void updateReview(int dif) {
        double sum = rating * numberOfReviews;
        sum += dif;
        rating = sum / numberOfReviews;
    }

    public String getName() {
        return name;
    }
}
