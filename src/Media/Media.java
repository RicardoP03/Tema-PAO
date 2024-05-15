package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class Media {
    protected int id;
    private String name;
    private double rating = 1;

    String category = "";

    public Media(String name, String category) {
        this.name = name;
        this.category = category;
        CRUD<Media> CR = CRUD.getInstance();
        this.id = CR.getNextId(Media.class);
    }

    public Media(int id, String name, double rating, String category) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.category = category;
    }
    public Media(Media md) {
        this.id = md.id;
        this.name = md.name;
        this.category = md.category;
        this.rating = md.rating;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + Integer.toString(this.id) + "\n";
        s += "Rating: " + Double.toString(rating) + "\n";
        s += "Category: " + this.category + "\n";
        return s;
    }

    public String shortDescription() {
        return toString();
    }

    public static Media parse(ResultSet res) {
        try {
            return new Media(res.getInt(1),
                         res.getString(2),
                         res.getDouble(3),
                         res.getString(4));
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
