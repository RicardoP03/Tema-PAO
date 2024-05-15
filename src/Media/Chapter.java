package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class Chapter {
    private int id;
    private String name;
    private int id_manga;

    public Chapter(String name, int id_manga) {
        this.name = name;
        CRUD<Chapter> CR = CRUD.getInstance();
        this.id = CR.getNextId(Chapter.class);
        this.id_manga = id_manga;
    }

    public Chapter(int id, String name, int id_manga) {
        this.name = name;
        this.id = id;
        this.id_manga = id_manga;
    }

    public static Chapter parse(ResultSet res)  {
        try {
            return new Chapter(res.getInt(1), res.getString(2), res.getInt(3));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String s;
        s = "name: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        return s;
    }
}
