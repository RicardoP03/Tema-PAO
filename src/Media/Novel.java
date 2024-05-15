package Media;

import Servicess.CRUD;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import Servicess.*;
public class Novel extends Media {
    int id;
    private String author;
    private Integer nrPages = 0;
    public Novel(String name, String author) {
        super(name, "novel");
        this.author = author;
        this.id = super.id;

        System.out.println("Romanul a fost creat");
    }

    private Novel(Media md, String Author, int nrPages) {
        super(md);
        this.id = super.getId();
        this.author = Author;
        this.nrPages = nrPages;
    }

    public static Novel parse(ResultSet res)  {
        try {
            CRUD<Media> cr = CRUD.getInstance();
            Media sup = cr.readByID(Media.class, res.getInt(1));
            return new Novel(sup, res.getString(2), res.getInt(3));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String toString() {
        String s;
        s = super.toString();
        s += "Romanul este scris de: ";
        s += this.author;
        s += "\n";
        s += "Lista volumelor\n\n";
        List<Volume> volumes = operationsService.getInstance().getVolumes(this.id);
        for(Volume vl: volumes) {
            s += vl.toString() + "\n";
        }
        return s;
    }

    public String shortDescription() {
        return super.toString();
    }

}
