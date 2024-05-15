package Media;

import Servicess.CRUD;

import java.sql.ResultSet;
import java.util.List;
import Servicess.*;
public class Anime extends Media {
    int id;
    private int duration = 0;
    public Anime(String name) {
        super(name, "anime");
        id = super.getId();
        System.out.println("Animeul a fost creat");
    }

    private Anime(Media md, int duration) {
        super(md);
        this.id = super.getId();
        this.duration = duration;
    }

    public static Anime parse(ResultSet res)  {
        try {
            CRUD<Media> cr = CRUD.getInstance();
            Media sup = cr.readByID(Media.class, res.getInt(1));
            return new Anime(sup, res.getInt(2));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        String s;
        s = super.toString();
        List<Episode> episodes = operationsService.getInstance().getEpisodes(this.id);
        s += "Animeul are: " + Integer.toString(episodes.size()) +  " episoade\n";
        s += "Lista episodeslor:\n\n";
        for(Episode ep: episodes) {
            s += ep.toString() + "\n";
        }
        return s;
    }

    public String shortDescription() {
        return super.toString();
    }

}
