package Media;

import Servicess.CRUD;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import Servicess.*;
public class Manga extends Media {
    int id;
    private String author;
    public Manga(String name, String author) {
        super(name, "manga");
        this.author = author;
        this.id = super.id;
        System.out.println("Seria manga a fost creata");
    }

    private Manga(Media md, String Author) {
        super(md);
        this.id = super.getId();
        this.author = Author;
    }

    public static Manga parse(ResultSet res)  {
        try {
            CRUD<Media> cr = CRUD.getInstance();
            Media sup = cr.readByID(Media.class, res.getInt(1));
            return new Manga(sup, res.getString(2));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        String s;
        s = super.toString();
        s += "Seria manga este scrisa de: ";
        s += this.author;
        s += "\n";
        s += "Lista capitolelor: \n\n";
        List<Chapter> chapters = operationsService.getInstance().getChapter(this.id);
        for(Chapter ch: chapters) {
            s += ch.toString() + "\n";
        }
        return s;
    }

    public String shortDescription() {
        return super.toString();
    }
}
