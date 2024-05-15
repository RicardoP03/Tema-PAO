package Media;

import Servicess.CRUD;

import java.sql.ResultSet;

public class WatchList {
    private int id;
    private int id_media;
    private int id_user;

    public WatchList(int id_media, int id_user) {
        CRUD<WatchList> CR = CRUD.getInstance();
        this.id = CR.getNextId(Media.class);
        this.id_media = id_media;
        this.id_user = id_user;
    }

    public WatchList(int id, int id_media, int id_user) {
        this.id = id;
        this.id_media = id_media;
        this.id_user = id_user;
    }

    public static WatchList parse(ResultSet res)  {
        try {
            CRUD<WatchList> cr = CRUD.getInstance();
            return new WatchList(res.getInt(1), res.getInt(2),
                    res.getInt(3));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getId() {
        return id;
    }

    public int getId_media() {
        return id_media;
    }
}
