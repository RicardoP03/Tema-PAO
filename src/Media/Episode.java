package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class Episode {
    private int id;
    private String name;
    private int duration;

    private int id_anime;


    public Episode(String name, int duration, int id_anime) {
        this.name = name;
        this.duration = duration;
        CRUD<Episode> CR = CRUD.getInstance();
        this.id = CR.getNextId(Episode.class);
        this.id_anime = id_anime;
        System.out.println("Episodul a fost creat");
    }

    public Episode(int id, String name, int duration, int id_anime) {
        this.name = name;
        this.duration = duration;
        this.id = id;
        this.id_anime = id_anime;
    }


    public static Episode parse(ResultSet res)  {
        try {
            return new Episode(res.getInt(1), res.getString(2),
                    res.getInt(4), res.getInt(3));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getDuration() {
        return this.duration;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        s += "Durata: " +  String.valueOf(this.duration) + " minute\n";
        return s;
    }

}
