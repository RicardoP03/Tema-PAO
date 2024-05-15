package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class Review {
    private int id;
    private int rating;
    private int id_media;
    private int id_user;

    public Review(int rating, int id_media, int id_user) {
        CRUD<Review> CR = CRUD.getInstance();
        this.id = CR.getNextId(Media.class);
        this.rating = rating;
        this.id_media = id_media;
        this.id_user = id_user;
    }

    public Review(int id, int rating, int id_media, int id_user) {
        this.id = id;
        this.rating = rating;
        this.id_media = id_media;
        this.id_user = id_user;
    }

    public static Review parse(ResultSet res)  {
        try {
            CRUD<Review> cr = CRUD.getInstance();
            return new Review(res.getInt(1), res.getInt(2),
                              res.getInt(3), res.getInt(4));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public int getId_media() { return id_media; }

    public int getRating() {
        return rating;
    }
}
