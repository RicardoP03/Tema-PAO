package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class Volume {
    private int id;
    private String name;
    private int pages;
    private  int id_novel;

    public Volume(String name, int pages, int id_novel) {
        this.name = name;
        this.pages = pages;
        CRUD<Volume> CR = CRUD.getInstance();
        this.id = CR.getNextId(Volume.class);
        this.id_novel = id_novel;
    }

    public Volume(int id, String name, int pages, int id_novel) {
        this.name = name;
        this.pages = pages;
        this.id = id;
        this.id_novel = id_novel;
    }

    public int getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public static Volume parse(ResultSet res)  {
        try {
            return new Volume(res.getInt(1), res.getString(2),
                              res.getInt(3), res.getInt(4));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString() {
        String s;
        s = "Nume: " + this.name + "\n";
        s += "ID: " + String.valueOf(this.id) + "\n";
        s += "Numar pagini: " + String.valueOf(this.pages) + "\n";
        return s;
    }

}
